package cn.bzu.hair.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.Goods;
import cn.bzu.hair.persistence.GoodsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Goods)表服务接口
 *
 * @author 高玉津
 * @since 2020-05-22 19:25:37
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper,Goods> {


    public List<Goods> queryGoods() {
        List<Goods> goodsList = this.list(new QueryWrapper<Goods>()
                .eq("is_deleted",0));
        return goodsList;
    }
}