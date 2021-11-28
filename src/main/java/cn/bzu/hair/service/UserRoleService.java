package cn.bzu.hair.service;

import cn.bzu.hair.domain.User;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.UserRole;
import cn.bzu.hair.persistence.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (UserRole)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-14 14:21:20
 */
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper,UserRole> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    // 用户注册
    @Transactional(rollbackFor = Exception.class)
    public UserRoleDto addUser(UserRoleDto dto) {
        // 校验
        UserRoleDto old = checkUser(dto);

        if (null != old) {
            return old;
        }
        // 生成user
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setUserPassword(dto.getUserPassword());
        user.setPhone(dto.getPhone());
        user.setBirthday(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setEmail(dto.getEmail());
        user.setIsDeleted(0);
        // 插入用户表
        userService.insertUser(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(dto.getRoleId());
        // 插入用户角色关系表
        this.save(userRole);

        // 返回结果
        dto.setUserId(user.getId());
        return dto;
    }

    // 校验数据重复
    public UserRoleDto checkUser(UserRoleDto dto) {
        Long id = dto.getUserId();


        // 用户名
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("user_name", dto.getUserName())
                .ne(null != id,"id", id), false);
        if (null != user) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setUserName(user.getUserName());
            return userRoleDto;
        }
        // 电话
        user = userService.getOne(new QueryWrapper<User>()
                .eq("phone", dto.getPhone())
                .ne(null != id,"id", id), false);
        if (null != user) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setPhone(user.getPhone());
            return userRoleDto;
        }
        // 邮箱
        user = userService.getOne(new QueryWrapper<User>()
                .eq("email", dto.getEmail())
                .ne(null != id,"id", id), false);
        if (null != user) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setEmail(user.getEmail());
            return userRoleDto;
        }
        return null;
    }


}