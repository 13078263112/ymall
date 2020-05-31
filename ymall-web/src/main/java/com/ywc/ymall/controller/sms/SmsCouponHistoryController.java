package com.ywc.ymall.controller.sms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.sms.service.CouponHistoryService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 0:14
 */
@CrossOrigin
@RestController
@Api(tags = "SmsCouponHistoryController", description = "优惠券领取记录管理")
@RequestMapping("/couponHistory")
public class SmsCouponHistoryController {
    @Reference
    private CouponHistoryService couponHistoryService;

    @ApiOperation("根据优惠券id，使用状态，订单编号分页获取领取记录")
    @GetMapping(value = "/list")
    public Object list(@RequestParam(value = "couponId", required = false) Long couponId,
                             @RequestParam(value = "useStatus", required = false) Integer useStatus,
                             @RequestParam(value = "orderSn", required = false) String orderSn,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo historyList = couponHistoryService.listCouponHistoryForPage(couponId, useStatus, orderSn, pageSize, pageNum);
        return new ResultParam().success(historyList);
    }

}
