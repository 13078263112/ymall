package com.ywc.ymall.oms.service;

import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.oms.entity.OrderSetting;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OrderReturnApplyParam;

import java.util.List;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface OrderReturnApplyService extends IService<OrderReturnApply> {

    PageInfoVo OrderReturnApply(OrderReturnApplyParam orderReturnApplyParam, Integer pageSize, Integer pageNum);

    OrderReturnApply  queryOrderReturnApplyById(Long id);

    void updateStatus(Long id, OrderReturnApply orderReturnApply);

    void delete(List<Long> ids);
}
