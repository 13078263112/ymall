package com.ywc.ymall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.Order;
import com.ywc.ymall.oms.mapper.OrderMapper;
import com.ywc.ymall.oms.service.OrderService;
import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OmsOrderQueryParam;
import com.ywc.ymall.vo.oms.OmsReceiverInfoParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public PageInfoVo pageOrder(OmsOrderQueryParam omsOrderQueryParam, Integer pageSize, Integer pageNum) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        //订单编号
        if(!StringUtils.isEmpty(omsOrderQueryParam.getOrderSn())){
            wrapper.like("order_sn",omsOrderQueryParam.getOrderSn());
        }
        //收货人模糊关键字
        if(!StringUtils.isEmpty(omsOrderQueryParam.getReceiverKeyword())){
            wrapper.like("receiver_name",omsOrderQueryParam.getReceiverKeyword());
        }
        //订单分类
        if(omsOrderQueryParam.getOrderType()!=null){
            wrapper.eq("order_type",omsOrderQueryParam.getOrderType());
        }
        //订单状态
        if(omsOrderQueryParam.getStatus()!=null) {
            wrapper.eq("status",omsOrderQueryParam.getStatus());
        }
       //订单来源
        if(omsOrderQueryParam.getSourceType()!=null) {
            wrapper.eq("source_type",omsOrderQueryParam.getSourceType());
        }
        //订单创建时间
        if(!StringUtils.isEmpty(omsOrderQueryParam.getCreateTime())) {
            wrapper.apply(!StringUtils.isEmpty(omsOrderQueryParam.getCreateTime()),"DATE_FORMAT( create_time, '%Y-%m-%d' ) " +
                    " >= DATE_FORMAT( '"+omsOrderQueryParam.getCreateTime()+"', '%Y-%m-%d' )");
        }
        IPage<Order> selectPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                selectPage.getRecords(),selectPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public Order queryOrderById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateReceiverInfo(OmsReceiverInfoParam omsReceiverInfoParam) {
        Order order = new Order();
        BeanUtils.copyProperties(omsReceiverInfoParam,order);
        order.setId(omsReceiverInfoParam.getOrderId());
        this.baseMapper.updateById(order);
    }

    @Override
    public void updateNote(Order order) {
        this.baseMapper.updateById(order);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateDelivery(List<OmsReceiverInfoParam> omsReceiverInfoParams) {
        for (OmsReceiverInfoParam omsReceiverInfoParam : omsReceiverInfoParams) {
            Order order = new Order();
            BeanUtils.copyProperties(omsReceiverInfoParam,order);
            order.setId(omsReceiverInfoParam.getOrderId());
            order.setStatus(2);
            this.baseMapper.updateById(order);
        }

    }

    @Override
    public void updateClose(List<Long> ids, String note) {
        for (Long id : ids) {
            Order order = new Order();
            order.setId(id);
            order.setNote(note);
            order.setStatus(4);
            this.baseMapper.updateById(order);
        }
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids) {
            this.baseMapper.deleteById(id);
        }
    }


}
