package cn.bzu.hair.web;

import cn.bzu.hair.domain.Role;
import cn.bzu.hair.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (Role)表控制层
 *
 * @author 高玉津
 * @since 2020-04-14 14:49:26
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


}