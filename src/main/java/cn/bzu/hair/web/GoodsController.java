package cn.bzu.hair.web;

import cn.bzu.hair.domain.Goods;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.service.GoodsService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Goods)表控制层
 *
 * @author 高玉津
 * @since 2020-05-22 19:25:37
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/data/query")
    public Msg getGoods(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size){


        PageMethod.startPage(page,size);
        List<Goods> list = goodsService.queryGoods();
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }


}