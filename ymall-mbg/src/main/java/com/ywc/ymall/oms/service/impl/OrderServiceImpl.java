package com.ywc.ymall.oms.service.impl;

import com.ywc.ymall.oms.entity.Order;
import com.ywc.ymall.oms.mapper.OrderMapper;
import com.ywc.ymall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
