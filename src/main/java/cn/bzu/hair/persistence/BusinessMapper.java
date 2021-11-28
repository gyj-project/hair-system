package cn.bzu.hair.persistence;

import cn.bzu.hair.domain.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * (Business)表Mapper接口
 *
 * @author 高玉津
 * @since 2020-04-18 15:40:12
 */
public interface BusinessMapper extends BaseMapper<Business> {
    List<String> listBusinessType();
}