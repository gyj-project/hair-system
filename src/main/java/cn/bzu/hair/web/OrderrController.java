package cn.bzu.hair.web;

import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.domain.Orderr;
import cn.bzu.hair.service.OrderrService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Orderr)表控制层
 *
 * @author 高玉津
 * @since 2020-05-22 23:33:50
 */
@RestController
@RequestMapping("/api/order")
public class OrderrController {



    @Autowired
    private OrderrService orderService;

    @PostMapping
    public Msg addOrder(@RequestBody Orderr orderr) {
        Long orderId = orderService.insertOrder(orderr);
        if ( orderId != 0) {
            return Msg.success().add("orderId", orderId);
        }

        return Msg.fail();
    }

    @GetMapping("/data/query")
    public Msg getOrder(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(required = false) String id){


        PageMethod.startPage(page,size);
        List<Orderr> list = orderService.queryOrder(id);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @DeleteMapping("/{id}")
    public Msg deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return Msg.success();
    }


}