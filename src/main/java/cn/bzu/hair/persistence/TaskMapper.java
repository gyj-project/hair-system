package cn.bzu.hair.persistence;

import cn.bzu.hair.domain.Task;
import cn.bzu.hair.dto.TaskDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * (Task)表Mapper接口
 *
 * @author 高玉津
 * @since 2020-04-25 16:02:47
 */
public interface TaskMapper extends BaseMapper<Task> {

    List<TaskDto> listByCondition(@Param("dto") TaskDto dto);

    List<Task> checkBarber(@Param("barberId") Long barberId, @Param("taskStart") Date taskStart, @Param("taskEnd") Date taskEnd);

    List<Task> checkCustomer(@Param("customerId") Long barberId, @Param("taskStart") Date taskStart, @Param("taskEnd") Date taskEnd);

    List<Task> remind();

    void deleteTimeById(@Param("id") Long id);



}