package cn.bzu.hair.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.bzu.hair.domain.BrokenPromise;
import cn.bzu.hair.persistence.BrokenPromiseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (BrokenPromise)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-27 14:09:59
 */
@Service
public class BrokenPromiseService extends ServiceImpl<BrokenPromiseMapper,BrokenPromise> {

    public List<BrokenPromise> queryBroken(String customerName) {
        List<BrokenPromise> brokenPromises = baseMapper.listByCondition(customerName);
        return brokenPromises;
    }

    public void updateBrokenPromiseByCustomerId(Long customerId) {

        BrokenPromise broken = this.getOne(new QueryWrapper<BrokenPromise>()
                .eq("customer_id", customerId), false);

        if (broken == null) {
            this.save(new BrokenPromise(null, customerId, 1, null));
        } else {
            broken.setBrokenNumber(broken.getBrokenNumber() + 1);
            this.updateById(broken);
        }

    }


    public void deleteBrokenById(Long id) {
        baseMapper.deleteById(id);
    }

    @Scheduled(cron = "0 0 2 1 * ?")
    public void deleteBreak(){
        this.remove(new QueryWrapper<BrokenPromise>().ge("id", 1));
    }

    public BrokenPromise queryByCustomerId(Long customerId) {
        return this.getOne(new QueryWrapper<BrokenPromise>().eq("customer_id", customerId), false);
    }
}