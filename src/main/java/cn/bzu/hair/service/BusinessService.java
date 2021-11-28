package cn.bzu.hair.service;

import cn.bzu.hair.domain.Skill;
import cn.bzu.hair.domain.Task;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.persistence.BusinessMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (Business)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-18 15:40:12
 */
@Service
public class BusinessService extends ServiceImpl<BusinessMapper,Business> {

    @Autowired
    private SkillService skillService;

    @Autowired
    private TaskService taskService;


    public List<Business> queryBusiness(String businessName, Double businessPrice,String businessType,
                                        Double priceFrom, Double priceTo) {

        List<Business> businessList = this.list(new QueryWrapper<Business>()
                .eq(!StringUtil.isNullOrEmpty(businessName), "business_name", businessName)
                .ge(priceFrom != null, "business_price", priceFrom)
                .le(priceTo != null, "business_price", priceTo)
                .eq(!StringUtil.isNullOrEmpty(businessType), "business_type", businessType)
                .eq("is_deleted",0));
        return businessList;

    }

    public Business queryById(Long id) {
        return this.getById(id);
    }

    public List<String> getBusinessType(){
        return baseMapper.listBusinessType();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateBusiness(Business business) {
        if (checkBusiness(business)) {
            baseMapper.updateById(business);
            skillService.updateSkillName(business.getId());
            return true;
        }
        return false;
    }

    public boolean checkBusiness(Business business) {
        Business result = this.getOne(new QueryWrapper<Business>()
                .ne(business.getId() != null, "id", business.getId())
                .eq("business_name", business.getBusinessName())
                .eq("is_deleted", 0), false);

        return result == null;
    }

   @Transactional(rollbackFor = Exception.class)
    public boolean addBusiness(Business business) {
        if (checkBusiness(business)) {
            this.save(business);
            return true;
        }
        return false;
    }

   @Transactional(rollbackFor = Exception.class)
    public boolean deleteBusinessById(Long id) {
        Business business = this.getOne(new QueryWrapper<Business>()
                .eq("id", id), false);
        business.setIsDeleted(1);
        if (taskService.getOne(new QueryWrapper<Task>()
                .eq("business_id",business.getId()), false) != null) {
            return false;
        }
        baseMapper.updateById(business);
        // 直接删掉与该业务相关的所有技能

        skillService.remove(new QueryWrapper<Skill>()
                .eq("business_id", id));
        return true;

    }
}