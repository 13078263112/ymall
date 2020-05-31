package com.ywc.ymall.controller.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.oms.entity.OrderSetting;
import com.ywc.ymall.oms.service.OrderSettingService;
import com.ywc.ymall.pms.entity.ProductAttribute;
import com.ywc.ymall.to.ResultParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 15:36
 */
@CrossOrigin
@RestController
@Api(tags = "OrderSettingController",description = "订单设置")
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Reference
    OrderSettingService orderSettingService;
    @ApiOperation("查询单个订单设置")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id){
        OrderSetting orderSetting=orderSettingService.queryOrderSettingById(id);
        return new ResultParam().success(orderSetting);
    }
    @ApiOperation("修改订单设置")
    @PostMapping(value = "/update/{id}")
    public Object update(@RequestBody OrderSetting  orderSetting) {
        orderSettingService.updateOrderSettingById(orderSetting);
        return new ResultParam().success(null);
    }
}
