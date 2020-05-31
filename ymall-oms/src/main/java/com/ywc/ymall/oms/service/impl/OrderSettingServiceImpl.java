package com.ywc.ymall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.OrderSetting;
import com.ywc.ymall.oms.mapper.OrderSettingMapper;
import com.ywc.ymall.oms.service.OrderSettingService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper, OrderSetting> implements OrderSettingService {

    @Override
    public OrderSetting queryOrderSettingById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateOrderSettingById(OrderSetting orderSetting) {
        this.baseMapper.updateById(orderSetting);
    }
}
