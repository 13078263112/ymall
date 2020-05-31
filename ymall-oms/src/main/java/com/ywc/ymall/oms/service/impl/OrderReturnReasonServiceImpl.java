package com.ywc.ymall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.oms.entity.OrderReturnReason;
import com.ywc.ymall.oms.mapper.OrderReturnReasonMapper;
import com.ywc.ymall.oms.service.OrderReturnReasonService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper, OrderReturnReason> implements OrderReturnReasonService {

    @Override
    public PageInfoVo OrderReturnReason(Integer pageSize, Integer pageNum) {
        Page<OrderReturnReason> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderReturnReason> wrapper = new QueryWrapper<>();
        IPage<OrderReturnReason> selectPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                selectPage.getRecords(),selectPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public void create(OrderReturnReason orderReturnReason) {
        orderReturnReason.setCreateTime(new Date());
        this.baseMapper.insert(orderReturnReason);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        OrderReturnReason orderReturnReason = new OrderReturnReason();
        orderReturnReason.setId(id);
        orderReturnReason.setStatus(status);
        this.baseMapper.updateById(orderReturnReason);
    }

    @Override
    public OrderReturnReason queryOrderReturnReasonById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateOrderReturnReason(OrderReturnReason orderReturnReason) {
        this.baseMapper.updateById(orderReturnReason);
    }

    @Override
    public void delete(List<Long> ids) {
        ids.stream().forEach(id->{
            this.baseMapper.deleteById(id);
        });
    }
}
