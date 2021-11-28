package cn.bzu.hair.service;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.dto.CancelDto;
import cn.bzu.hair.dto.TaskDto;
import cn.bzu.hair.message.config.AppConfig;
import cn.bzu.hair.message.lib.MessageSend;
import cn.bzu.hair.utils.DateUtil;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.Task;
import cn.bzu.hair.persistence.TaskMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (Task)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-25 16:02:47
 */
@Service
public class TaskService extends ServiceImpl<TaskMapper,Task> {
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BrokenPromiseService brokenPromiseService;
    @Autowired
    private UserService userService;


    public List<TaskDto> selectTasks(String customerName,
                                     String barberName,
                                     String businessName,
                                     String dateStart,
                                     String dateEnd){
        TaskDto dto = new TaskDto();
        if (!StringUtil.isNullOrEmpty(customerName)) {
           dto.setCustomerName(customerName);
        }
        if (!StringUtil.isNullOrEmpty(barberName)) {
           dto.setBarberName(barberName);
        }
        if (!StringUtil.isNullOrEmpty(businessName)) {
            dto.setBusinessName(businessName);
        }
        if (!StringUtil.isNullOrEmpty(dateStart) && !dateStart.equals("Invalid date")) {

            dto.setDateStart(DateUtil.stringToDate(dateStart));
        }

        if (!StringUtil.isNullOrEmpty(dateEnd) && !dateEnd.equals("Invalid date")) {
            Calendar calendar  =  new GregorianCalendar();
            calendar.setTime(DateUtil.stringToDate(dateEnd));
            calendar.add(calendar.DATE,1);
            dto.setDateEnd(calendar.getTime());
        }


        return baseMapper.listByCondition(dto);

    }
    @Transactional(rollbackFor = Exception.class)
    public Msg addTask(Task task) {

        Lock lock = new ReentrantLock();
        lock.lock();

        task = setTime(task);

        String result = checkTask(task);

        if (result != null) {

            return Msg.fail().add("error", result);
        }
        // 设置其他属性
        task.setIsRemind(0);
        task.setCreatedTime(new Date());

        this.save(task);

        lock.unlock();

        return Msg.success();



    }
    @Transactional(rollbackFor = Exception.class)
    public Msg updateTask(Task task) {
        Lock lock = new ReentrantLock();
        lock.lock();

        // 暂存task，防止修改错误无法回滚数据
        Task oldTask = this.getById(task.getId());

        // 删除原来的时间
        baseMapper.deleteTimeById(task.getId());
        task = setTime(task);

        String result = checkTask(task);
        if (result != null) {

            baseMapper.updateById(oldTask);
            return Msg.fail().add("error", result);
        }

        baseMapper.updateById(task);
        lock.unlock();

        return Msg.success();
    }

    public Task setTime(Task task) {
        // 根据业务id查询业务所需时间
        Business business = businessService.getById(task.getBusinessId());
        task.setTaskSpendTime(business.getBusinessTime());

        // 计算并设置服务结束时间
        Double hours =  Double.parseDouble(business.getBusinessTime());
        double time = task.getTaskStart().getTime() + hours * 60 * 60 * 1000;
        task.setTaskEnd(new Date((long) time));

        return task;
    }




    public String checkTask(Task task) {

        if (task.getTaskStart().before(new Date())) {
            return "不能预约之前的时间";
        }

        if (task.getTaskStart().getHours() < 7 || task.getTaskEnd().getHours() > 18) {
            return "不在营业时间内";
        }


        // 查询该时间段该理发师是否有其他任务
        List<Task> error1 = baseMapper.checkBarber(task.getBarberId(), task.getTaskStart(), task.getTaskEnd());

        if (error1.size() != 0) {
            return "该时间段理发师已被预约其他服务";
        }

        // 查询该时间段该会员是否预约了其他服务
        List<Task> error2 = baseMapper.checkCustomer(task.getCustomerId(), task.getTaskStart(), task.getTaskEnd());

        if (error2.size() != 0) {
            return "该时间段您已预约其他服务";
        }
        return null;

    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTask(CancelDto dto) {
        Long roleId = dto.getRoleId();
        Long taskId = dto.getTaskId();
        Task task = this.getById(taskId);
        task.setIsDeleted(1);
        baseMapper.updateById(task);
        if (roleId == 3) {
            Long customerId = task.getCustomerId();
            brokenPromiseService.updateBrokenPromiseByCustomerId(customerId);
        }
    }

    @Scheduled(fixedRate=600000000)
    public void remindTasks() {

        List<Task> tasks = baseMapper.remind();

        if (tasks.size() > 0) {

            for (Task task : tasks) {

                Long customerId = task.getCustomerId();
                User user = userService.getById(customerId);
                String phone = user.getPhone();

                Long businessId = task.getBusinessId();
                Business business = businessService.getById(businessId);
                String businessName = business.getBusinessName();

                AppConfig config = new AppConfig();
                MessageSend submail = new MessageSend(config);
                submail.addTo(phone);
                submail.addContent("【短信测试】尊敬的"+
                        user.getUserName()+"，距离您的预约的"+
                        businessName+"服务还剩1小时，请您及时到理发店内完成服务，谢谢");
                submail.send();

                task.setIsRemind(1);
                baseMapper.updateById(task);
            }
        }

    }

    /*@Scheduled(fixedRate=6000)
    public void autoDeleteTasks() {
        // 删除预约时间小于当前时间的数据
        Date now = new Date();

        List<Task> tasks = baseMapper.list(new QueryWrapper<Task>()
                .le("task_start", now));

        tasks.stream().forEach(e ->{
            e.setIsDeleted(1);
            baseMapper.updateById(e);
        });
        
    }*/



}