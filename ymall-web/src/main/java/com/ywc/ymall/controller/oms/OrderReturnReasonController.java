package com.ywc.ymall.controller.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.oms.entity.OrderReturnReason;
import com.ywc.ymall.oms.service.OrderReturnApplyService;
import com.ywc.ymall.oms.service.OrderReturnReasonService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductParam;
import com.ywc.ymall.vo.oms.OrderReturnApplyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 17:28
 */
@CrossOrigin
@RestController
@Api(tags = "OrderReturnReasonController",description = "退货原因设置")
@RequestMapping("/returnReason")
public class OrderReturnReasonController {
    @Reference
    OrderReturnReasonService orderReturnReasonService;
    @ApiOperation("查询退货原因设置")
    @GetMapping(value = "/list")
    public Object getList(@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo pageInfoVo=orderReturnReasonService.OrderReturnReason(pageSize, pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("创建退货原因")
    @PostMapping(value = "/create")
    public Object create( @RequestBody OrderReturnReason orderReturnReason) {
        orderReturnReasonService.create(orderReturnReason);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改退货原因状态")
    @PostMapping(value = "/update/status")
    public Object updateStatus(@RequestParam("ids") Long id,
                               @RequestParam("status") Integer status) {
        orderReturnReasonService.updateStatus(id,status);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改退货原因状态")
    @PostMapping(value = "/update/{id}")
    public Object update(@PathVariable Long id,
                         @RequestBody OrderReturnReason orderReturnReason) {
        orderReturnReasonService.updateOrderReturnReason(orderReturnReason);
        return new ResultParam().success(null);
    }
    @ApiOperation("查询单个退货原因")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id){
        OrderReturnReason orderReturnReason=orderReturnReasonService.queryOrderReturnReasonById(id);
        return new ResultParam().success(orderReturnReason);
    }
    @ApiOperation("删除退货原因")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        orderReturnReasonService.delete(ids);
        return new ResultParam().success(null);
    }
}
