package cn.bzu.hair.service;

import cn.bzu.hair.domain.*;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.persistence.SkillMapper;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.persistence.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-14 21:09:58
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {


    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private TaskService taskService;


    public void insertUser(User user) {
       this.save(user);
    }

    public List<UserRoleDto> queryUser(Long userId,String userName, Long roleId, String phone, String gender) {
        if (StringUtil.isNullOrEmpty(userName)) {
            userName = null;
        }
        if (StringUtil.isNullOrEmpty(gender)) {
            gender = null;
        }
        if (StringUtil.isNullOrEmpty(phone)) {
            phone = null;
        }
        User user = new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setPhone(phone);
        user.setGender(gender);
        Role role = new Role();
        role.setId(roleId);

        List<UserRoleDto> userDto = baseMapper.listByCondition(user, role);
        return userDto;
    }

    public UserRoleDto userLogin(User user) {
        String userName = user.getUserName();
        String userPassword = user.getUserPassword();
        User userInfo = this.getOne(new QueryWrapper<User>()
                .eq("user_name", userName)
                .eq("user_password", userPassword), false);
        if (null == userInfo) {
            return null;
        }
        Long id = userInfo.getId();
        List<UserRoleDto> dto = queryUser(id, userName, null, null, null);
        if (dto.size() == 1) {
            return dto.get(0);
        }


        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public Msg updateUser(UserRoleDto dto) {
        if (null != userRoleService.checkUser(dto)){
            return Msg.fail().add("msg","数据重复！");
        }

        // 旧user
        User oldUser = this.getById(dto.getUserId());
        // 新user
        User newUser = new User();
        newUser.setId(dto.getUserId());
        newUser.setUserName(dto.getUserName());
        newUser.setPhone(dto.getPhone());
        newUser.setUserPassword(dto.getUserPassword());
        newUser.setIsDeleted(dto.getIsDeleted());

        // 校验Task
        Task task = taskService.getOne(new QueryWrapper<Task>().eq("barber_id", oldUser.getId()), false);
        if (task != null && oldUser.getIsDeleted() != newUser.getIsDeleted()) {
           // return Msg.fail().add("msg","该理发师现在仍有预约任务，不允许禁用！");
        }

        baseMapper.updateById(newUser);
        // 更新skill表
        if (newUser.getIsDeleted() != oldUser.getIsDeleted()) {
            if (newUser.getIsDeleted() == 0) {
                skillMapper.updateSkillOpen(newUser.getId());
            }
            if (newUser.getIsDeleted() == 1) {
                skillMapper.updateSkillClose(newUser.getId());
            }
        }
        if (newUser.getUserName() != oldUser.getUserName()) {
            skillMapper.updateSkillBarberName(newUser.getId());
        }

        return Msg.success();
    }

    public boolean findPassword(User user) {
        User result = this.getOne(new QueryWrapper<User>()
                .eq("user_name", user.getUserName())
                .eq("email", user.getEmail()), false);
        if (result == null) {
            return false;
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    MimeMessageHelper helper = null;

                    helper = new MimeMessageHelper(mimeMessage, true);

                    helper.setSubject("邮箱测试");
                    helper.setText("尊敬的用户,你的密码是：<b style='color:red'>" + result.getUserPassword() + "</b>", true);

                    helper.setTo(result.getEmail());
                    helper.setFrom("1807069286@qq.com");

                    mailSender.send(mimeMessage);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return true;

    }

}