package cn.bzu.hair.service;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.bzu.hair.domain.PublishInfo;
import cn.bzu.hair.persistence.PublishInfoMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * (PublishInfo)表服务接口
 *
 * @author 高玉津
 * @since 2020-04-29 00:18:44
 */
@Service
public class PublishInfoService extends ServiceImpl<PublishInfoMapper,PublishInfo> {


    public List<PublishInfo> queryInfo(String title) {

        List<PublishInfo> infoList = this.list(new QueryWrapper<PublishInfo>()
                .like(!StringUtil.isNullOrEmpty(title), "title", title)
                .eq("is_deleted",0)
                .orderByDesc("publish_time"));
        return infoList;

    }

    public PublishInfo queryById(Long id) {
        PublishInfo publishInfo = this.getById(id);
        return publishInfo;
    }

    public void addPublishInfo(PublishInfo publishInfo) {
        Date date = new Date();
        publishInfo.setPublishTime(date);
        publishInfo.setIsDeleted(0);
        this.save(publishInfo);
    }

    public void deletePublishInfoById(Long id) {
        baseMapper.deleteById(id);
    }
}