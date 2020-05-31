package com.ywc.ymall.controller.oms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.ywc.ymall.oms.entity.Order;
import com.ywc.ymall.oms.service.OrderService;
import com.ywc.ymall.to.ResultParam;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.PmsProductQueryParam;
import com.ywc.ymall.vo.oms.OmsOrderQueryParam;
import com.ywc.ymall.vo.oms.OmsReceiverInfoParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 13:12
 */
@CrossOrigin
@RestController
@Api(tags = "OrderController",description = "订单管理")
@RequestMapping("/order")
public class OrderController {
    @Reference
    OrderService orderService;

    @ApiOperation("查询订单")
    @GetMapping(value = "/list")
    public Object getList(OmsOrderQueryParam omsOrderQueryParam,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageInfoVo pageInfoVo=orderService.pageOrder(omsOrderQueryParam, pageSize, pageNum);
        return new ResultParam().success(pageInfoVo);
    }
    @ApiOperation("根据id获取订单信息")
    @GetMapping(value = "/{id}")
    public Object getItem(@PathVariable Long id) {
        return new ResultParam().success(orderService.queryOrderById(id));
    }
    @ApiOperation("修改收货人信息")
    @PostMapping(value = "/update/receiverInfo")
    public Object updateReceiverInfo(@RequestBody OmsReceiverInfoParam omsReceiverInfoParam) {
        orderService.updateReceiverInfo(omsReceiverInfoParam);
        return new ResultParam().success(null);
    }
    @ApiOperation("修改备注订单")
    @PostMapping(value = "/update/note")
    public Object updateNote(Order order) {
        orderService.updateNote(order);
        return new ResultParam().success(null);
    }
    @ApiOperation("删除订单")
    @GetMapping(value = "/delete/{id}")
    public Object delete(@PathVariable Long id) {

        orderService.deleteById(id);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量发货订单")
    @PostMapping(value = "/update/delivery")
    public Object updateDelivery(@RequestBody List<OmsReceiverInfoParam> omsReceiverInfoParams) {
        orderService.updateDelivery(omsReceiverInfoParams);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量关闭订单")
    @PostMapping(value = "/update/close")
    public Object updateClose(@RequestParam("ids") List<Long> ids,
                              @RequestParam("note") String note) {
        orderService.updateClose(ids,note);
        return new ResultParam().success(null);
    }
    @ApiOperation("批量删除订单")
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("ids") List<Long> ids) {
        orderService.deleteByIds(ids);
        return new ResultParam().success(null);
    }
}
