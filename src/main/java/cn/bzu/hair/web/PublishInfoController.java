package cn.bzu.hair.web;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.PublishInfo;
import cn.bzu.hair.service.PublishInfoService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (PublishInfo)表控制层
 *
 * @author 高玉津
 * @since 2020-04-29 00:18:44
 */
@RestController
@CrossOrigin
@RequestMapping("/api/publish/info")
public class PublishInfoController {

    @Autowired
    private PublishInfoService publishInfoService;

    @GetMapping("/data/query")
    public Msg getPublish(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(required = false) String title){


        PageMethod.startPage(page,size);
        List<PublishInfo> list = publishInfoService.queryInfo(title);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @GetMapping("/{id}")
    public Msg getPublishById(@PathVariable("id") Long id) {
        PublishInfo publishInfo = publishInfoService.queryById(id);
        return Msg.success().add("publishInfo", publishInfo);
    }

    @PostMapping
    public Msg addPublishInfo(@RequestBody PublishInfo publishInfo) {

        publishInfoService.addPublishInfo(publishInfo);
        return Msg.success();
    }

    @DeleteMapping("/{id}")
    public Msg deletePublishInfo(@PathVariable("id") Long id) {
        publishInfoService.deletePublishInfoById(id);
        return Msg.success();
    }


}