package cn.bzu.hair.web;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.Task;
import cn.bzu.hair.dto.CancelDto;
import cn.bzu.hair.dto.TaskDto;
import cn.bzu.hair.service.TaskService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * (Task)表控制层
 *
 * @author 高玉津
 * @since 2020-04-25 16:02:47
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/data/query")
    public Msg getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "size", defaultValue = "5") int size,
                        @RequestParam(required = false) String customerName,
                        @RequestParam(required = false) String barberName,
                        @RequestParam(required = false) String businessName,
                        @RequestParam(required = false) String dateStart,
                        @RequestParam(required = false) String dateEnd){

        PageMethod.startPage(page,size);
         List<TaskDto> list = taskService.selectTasks(customerName, barberName, businessName, dateStart, dateEnd);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @PostMapping
    public Msg addTask(@RequestBody Task task) {
        Msg msg = taskService.addTask(task);
        return msg;
    }

    @PutMapping
    public Msg updateTask(@RequestBody Task task) {
        Msg msg = taskService.updateTask(task);
        return msg;
    }
    @PostMapping("/cancel")
    public Msg deleteSkill(@RequestBody CancelDto dto) {
        taskService.deleteTask(dto);
        return Msg.success();
    }


}