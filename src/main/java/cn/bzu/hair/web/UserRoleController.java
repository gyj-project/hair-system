package cn.bzu.hair.web;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * (UserRole)表控制层
 *
 * @author 高玉津
 * @since 2020-04-14 14:21:20
 */
@RestController
@RequestMapping("/api")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    @PostMapping("/add/user")
    public Msg addUser(@RequestBody UserRoleDto dto) {
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