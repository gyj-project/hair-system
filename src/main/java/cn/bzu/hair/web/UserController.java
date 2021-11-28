package cn.bzu.hair.web;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.service.UserRoleService;
import cn.bzu.hair.service.UserService;
import cn.bzu.hair.utils.IpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author 高玉津
 * @since 2020-04-14 14:00:34
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/data/query")
    public Msg getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "size", defaultValue = "5") int size,
                        @RequestParam(required = false) Long userId,
                        @RequestParam(required = false) String userName,
                        @RequestParam(required = false) Long roleId,
                        @RequestParam(required = false) String phone,
                        @RequestParam(required = false) String gender){


        PageMethod.startPage(page,size);
        List<UserRoleDto> list = userService.queryUser(userId,userName,roleId,phone,gender);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @PostMapping("/login")
    public Msg userLogin(@RequestBody User user, HttpServletRequest request){


        UserRoleDto dto = userService.userLogin(user);
        if (null == dto) {
            return Msg.fail();
        } else {
            String ip = IpUtil.getIpAddr(request);
            // redisTemplate.opsForValue().set(ip, user.getUserName());
            return Msg.success().add("user",dto);
        }

    }
    @GetMapping("/logout")
    public void userLogout(HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        // redisTemplate.delete(ip);
    }

    @GetMapping("/query/barber")
    public Msg getBarber(@RequestParam(required = false) Long userId,
                        @RequestParam(required = false) String userName){
       
        List<UserRoleDto> list = userService.queryUser(userId,userName,new Long(2),null,null);
        return Msg.success().add("barbers", list);
    }

    @PutMapping
    public Msg updateUser(@RequestBody UserRoleDto user) {
        Msg msg = userService.updateUser(user);
        return msg;
    }

    @PostMapping("/find/pass")
    public Msg findPassword(@RequestBody User user) {
        if (userService.findPassword(user)) {
            return Msg.success().add("msg","密码已发至您的邮箱");
        } else {
            return Msg.fail().add("msg","用户名和邮箱不匹配");
        }

    }



    @PostMapping("/regist")
    public Msg registUser(@RequestBody UserRoleDto dto) {
        UserRoleDto result = userRoleService.addUser(dto);
        if (null != result.getUserId()) {
            return Msg.success().add("msg", result);
        } else{
            if (null != result.getUserName()) {
                return Msg.fail().add("msg", "该用户名已注册");
            }
            if (null != result.getPhone()) {
                return Msg.fail().add("msg", "该手机号已注册");
            }
            if (null != result.getUserName()) {
                return Msg.fail().add("msg", "该邮箱已注册");
            }
        }
        return Msg.fail();
    }




}