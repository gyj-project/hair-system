package cn.bzu.hair.service;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.domain.User;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.bzu.hair.domain.Skill;
import cn.bzu.hair.persistence.SkillMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * (Skill)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-20 14:02:45
 */
@Service
public class SkillService extends ServiceImpl<SkillMapper,Skill> {
    @Autowired
    private UserService userService;
    @Autowired
    private BusinessService businessService;






    public List<Skill> querySkills(Long id, String businessName, String barberName) {

        List<Skill> skills = this.list(new QueryWrapper<Skill>()
                .eq(null != id, "id", id)
                .like(!StringUtil.isNullOrEmpty(businessName), "business_name", businessName)
                .like(!StringUtil.isNullOrEmpty(barberName), "barber_name", barberName)
                .eq("is_closed",0)
                .orderByAsc("barber_id"));

        return skills;
    }

    public boolean updateSkill(Skill skill) {
        if (checkSkill(skill)) {
            User user = userService.getById(skill.getBarberId());
            skill.setBarberName(user.getUserName());
            Business business = businessService.getById(skill.getBusinessId());
            skill.setBusinessName(business.getBusinessName());
            this.updateById(skill);
            return true;
        }
        return false;
    }



    public boolean addSkill(Skill skill) {
        if (checkSkill(skill)) {
            User user = userService.getById(skill.getBarberId());
            skill.setBarberName(user.getUserName());
            Business business = businessService.getById(skill.getBusinessId());
            skill.setBusinessName(business.getBusinessName());
            this.save(skill);
            return true;
        }
        return false;
    }
    public boolean checkSkill(Skill skill) {
        Skill result = this.getOne(new QueryWrapper<Skill>()
                .ne(skill.getId() != null, "id", skill.getId())
                .eq("barber_id", skill.getBarberId())
                .eq("business_id", skill.getBusinessId())
                .eq("is_closed", 0), false);
        return result == null;
    }
    public void deleteSkillById(Long id) {
        this.removeById(id);
    }

    public void updateSkillName(Long id) {
        baseMapper.updateSkillBusinessName(id);
    }

    public List<Skill> getSkillByBusinessId(Long businessId) {
        return this.list(new QueryWrapper<Skill>().eq("business_id", businessId));
    }

    public UUID importSkillInfo(InputStream in, Long tenantId) throws Exception {

        return null;
//
//        UUID batchNumber = UUID.randomUUID();
//        ExcelImportHandler<SkillTemp> excelImportHandler = new ExcelImportHandler<SkillTemp>(){
//            // 得到行号
//            @Override
//            public String getRowNumberColumnName() {
//                return "lineNumber";
//            }
//            // 清除历史记录
//            @Override
//            public void clearHistoryData() {
//                skillTempService.deleteTemp();
//            }
//            // 得到历史记录
//            @Override
//            public Class<SkillTemp> getEntityClass() {
//                return SkillTemp.class;
//            }
//            // 持久化
//            @Override
//            public List<SkillTemp> persistence(List<SkillTemp> list) {
//
//                list.stream().forEach(temp -> {
//
//                    temp.setIsClosed(0);
//
//                    User user = userService.getOne(new QueryWrapper<User>()
//                            .eq("user_name", temp.getBarberName()));
//                    if (null != user) {
//                        temp.setBarberId(user.getId());
//                    }
//
//                    Business business = businessService.getOne(new QueryWrapper<Business>()
//                            .eq("business_name", temp.getBusinessName()));
//                    if (null != business) {
//                        temp.setBusinessId(business.getId());
//                    }
//
//                });
//
//                skillTempService.insertBatch(list);
//                return list;
//            }
//            // 检查
//            @Override
//            public void check(List<SkillTemp> list) {
//                checkImportData(list, batchNumber.toString());
//            }
//
//
//        };
//        ExcelImportService excelImportService = new ExcelImportService();
//        excelImportService.importExcel(in, false, 5, excelImportHandler);
//        skillTempService.confirmImport();
//        skillTempService.deleteTemp();
//
//        return batchNumber;
    }

}