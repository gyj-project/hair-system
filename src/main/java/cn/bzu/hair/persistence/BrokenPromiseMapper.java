package cn.bzu.hair.persistence;

import cn.bzu.hair.domain.BrokenPromise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (BrokenPromise)表Mapper接口
 *
 * @author 高玉津
 * @since 2020-04-27 14:10:00
 */
public interface BrokenPromiseMapper extends BaseMapper<BrokenPromise> {
    List<BrokenPromise> listByCondition(@Param("customerName") String customerName);
}