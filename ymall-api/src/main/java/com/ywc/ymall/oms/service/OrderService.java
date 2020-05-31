package com.ywc.ymall.oms.service;

import com.ywc.ymall.oms.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OmsOrderQueryParam;
import com.ywc.ymall.vo.oms.OmsReceiverInfoParam;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface OrderService extends IService<Order> {


    PageInfoVo pageOrder(OmsOrderQueryParam omsOrderQueryParam, Integer pageSize, Integer pageNum);

    Order queryOrderById(Long id);


    void updateReceiverInfo(OmsReceiverInfoParam omsReceiverInfoParam);

    void updateNote(Order order);

    void deleteById(Long id);

    void updateDelivery(List<OmsReceiverInfoParam> omsReceiverInfoParams);

    void updateClose(List<Long> ids, String note);

    void deleteByIds(List<Long> ids);
}
