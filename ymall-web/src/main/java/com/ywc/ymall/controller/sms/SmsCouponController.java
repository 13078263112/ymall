package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.service.CouponService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.SmsCouponParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 23:57
 */
@CrossOrigin
@RestController
@Api(tags = "SmsCouponController", description = "优惠券管理")
@RequestMapping("/coupon")
public class SmsCouponController {
    @Reference
    CouponService couponService;

    @ApiOperation("根据优惠券名称和类型分页获取优惠券列表")
    @GetMapping(value = "/list")
    public Object list(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "type",required = false) Integer type,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo couponList = couponService.listForPage(name,type,pageSize,pageNum);
        return new ResultParam().success(couponList);
    }
    @ApiOperation("获取单个优惠券的详细信息")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id) {
        SmsCouponParam couponParam = couponService.getCouponItemInfo(id);
        return new ResultParam().success(couponParam);
    }
    @ApiOperation("添加优惠券")
    @PostMapping(value = "/create")
    public Object add(@RequestBody SmsCouponParam couponParam) {
         couponService.create(couponParam);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改优惠券")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Long id,@RequestBody SmsCouponParam couponParam) {
         couponService.updateCouponInfos(id,couponParam);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除优惠券")
    @PostMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {
       couponService.deleteById(id);
        return new ResultParam().success(null);

    }
}
