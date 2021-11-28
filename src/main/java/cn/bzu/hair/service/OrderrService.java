package cn.bzu.hair.service;

import cn.bzu.hair.domain.Goods;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.Orderr;
import cn.bzu.hair.persistence.OrderrMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Orderr)表服务接口
 *
 * @author 高玉津
 * @since 2020-05-22 23:33:50
 */
@Service
public class OrderrService extends ServiceImpl<OrderrMapper,Orderr> {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserService userService;



    public Long insertOrder(Orderr orderr) {
        Goods goods = goodsService.getById(orderr.getGoodsId());
        User user = userService.getById(orderr.getCustomerId());
        if (goods.getGoodsNum() < 1) {
            return new Long(0);
        }
        orderr.setId(null);
        orderr.setCustomerName(user.getUserName());
        orderr.setGoodsName(goods.getGoodsName());

        this.save(orderr);

        return orderr.getId();
    }

    public List<Orderr> queryOrder(String id) {

        List<Orderr> orderrs = this.list(new QueryWrapper<Orderr>()
                .eq(!StringUtil.isNullOrEmpty(id), "id", id)
                .eq("is_pay", 1)
                .orderByDesc("id"));
        return orderrs;
    }

    public void deleteOrder(Long id) {
        baseMapper.deleteById(id);
    }
}