package cn.bzu.hair.web;

import cn.bzu.hair.domain.BrokenPromise;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.service.BrokenPromiseService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (BrokenPromise)表控制层
 *
 * @author 高玉津
 * @since 2020-04-27 14:09:59
 */
@RestController
@RequestMapping("/api/broken/promise")
public class BrokenPromiseController {

    @Autowired
    private BrokenPromiseService brokenPromiseService;


    @GetMapping("/data/query")
    public Msg getBroken(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(required = false) String customerName) {

        PageMethod.startPage(page,size);
        List<BrokenPromise> list = brokenPromiseService.queryBroken(customerName);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }


    @DeleteMapping("/{id}")
    public Msg deleteBusiness(@PathVariable("id") Long id) {
        brokenPromiseService.deleteBrokenById(id);
        return Msg.success();
    }

    @GetMapping("/query/by/customerId")
    public Msg getByCustomerId(@RequestParam(value = "customerId") Long id) {

        BrokenPromise brokenPromise = brokenPromiseService.queryByCustomerId(id);

        return Msg.success().add("brokenPromise", brokenPromise);
    }


}