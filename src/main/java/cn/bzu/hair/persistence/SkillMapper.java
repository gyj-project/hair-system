package cn.bzu.hair.persistence;

import cn.bzu.hair.domain.Skill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * (Skill)表Mapper接口
 *
 * @author 高玉津
 * @since 2020-04-20 14:02:45
 */
public interface SkillMapper extends BaseMapper<Skill> {
    void updateSkillBusinessName(@Param("businessId") Long businessId);
    void updateSkillBarberName(@Param("barberId") Long barberId);
    void updateSkillOpen(@Param("barberId") Long barberId);
    void updateSkillClose(@Param("barberId") Long barberId);

}