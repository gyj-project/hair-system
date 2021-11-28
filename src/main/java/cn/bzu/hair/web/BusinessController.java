package cn.bzu.hair.web;

import cn.bzu.hair.domain.Business;
import cn.bzu.hair.domain.Msg;
import cn.bzu.hair.dto.UserRoleDto;
import cn.bzu.hair.service.BusinessService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Business)表控制层
 *
 * @author 高玉津
 * @since 2020-04-18 15:40:12
 */
@RestController
@RequestMapping("/api/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    @GetMapping("/data/query")
    public Msg getBusiness(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "size", defaultValue = "5") int size,
                           @RequestParam(required = false) String businessName,
                           @RequestParam(required = false) Double businessPrice,
                           @RequestParam(required = false) String businessType,
                           @RequestParam(required = false) Double priceFrom,
                           @RequestParam(required = false) Double priceTo){


        PageMethod.startPage(page,size);
        List<Business> list = businessService.queryBusiness(businessName, businessPrice,businessType
        ,priceFrom, priceTo);
        PageInfo pageInfo = new PageInfo(list,6);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @GetMapping("/{id}")
    public Msg getBusinessById(@PathVariable("id") Long id) {
        Business business = businessService.queryById(id);
        return Msg.success().add("business", business);
    }

    @PutMapping()
    public Msg updateBusiness(@RequestBody Business business) {
        if (businessService.updateBusiness(business)) {
            return Msg.success();
        }
        return Msg.fail();
    }
    @PostMapping()
    public Msg addBusiness(@RequestBody Business business) {
        if (businessService.addBusiness(business)) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @GetMapping("/types")
    public Msg getBusinessType() {
        List<String> list = businessService.getBusinessType();
        return Msg.success().add("types", list);
    }

    @DeleteMapping("/{id}")
    public Msg deleteBusiness(@PathVariable("id") Long id) {
        if(businessService.deleteBusinessById(id)){
            return Msg.success();
        } else {
            return Msg.fail();
        }

    }



}