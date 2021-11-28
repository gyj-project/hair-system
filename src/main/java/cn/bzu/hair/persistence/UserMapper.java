package cn.bzu.hair.persistence;



import cn.bzu.hair.domain.Role;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.dto.UserRoleDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (User)表Mapper接口
 *
 * @author 高玉津
 * @since 2020-04-14 14:00:34
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserRoleDto> listByCondition(@Param("user") User user, @Param("role") Role role);
}