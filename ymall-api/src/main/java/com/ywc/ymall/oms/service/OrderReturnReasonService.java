package com.ywc.ymall.oms.service;

import com.ywc.ymall.oms.entity.OrderReturnReason;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 退货原因表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface OrderReturnReasonService extends IService<OrderReturnReason> {

    PageInfoVo OrderReturnReason(Integer pageSize, Integer pageNum);

    void create(OrderReturnReason orderReturnReason);

    void updateStatus(Long id, Integer status);

    OrderReturnReason queryOrderReturnReasonById(Long id);

    void updateOrderReturnReason(OrderReturnReason orderReturnReason);

    void delete(List<Long> ids);
}
