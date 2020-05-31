package com.ywc.ymall.controller.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.oms.entity.Order;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.oms.entity.OrderSetting;
import com.ywc.ymall.oms.service.OrderReturnApplyService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OrderReturnApplyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 15:47
 */
@CrossOrigin
@RestController
@Api(tags = "OrderReturnApplyController",description = "退货管理")
@RequestMapping("/returnApply")
public class OrderReturnApplyController {
    @Reference
    OrderReturnApplyService orderReturnApplyService;

    @ApiOperation("查询退货订单")
    @GetMapping(value = "/list")
    public Object getList(OrderReturnApplyParam OrderReturnApplyParam,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo pageInfoVo=orderReturnApplyService.OrderReturnApply(OrderReturnApplyParam, pageSize, pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("查询单个退货订单")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id){
        OrderReturnApply orderReturnApply=orderReturnApplyService.queryOrderReturnApplyById(id);
        return new ResultParam().success(orderReturnApply);
    }
    @ApiOperation("修改退货订单")
    @PostMapping(value = "/update/status/{id}")
    public Object updateStatus(@PathVariable Long id,
                             @RequestBody OrderReturnApply orderReturnApply) {
        orderReturnApplyService.updateStatus(id,orderReturnApply);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除退货订单")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        orderReturnApplyService.delete(ids);
        return new ResultParam().success(null);
    }
}
