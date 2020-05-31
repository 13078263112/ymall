package com.ywc.ymall.oms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.oms.entity.Order;
import com.ywc.ymall.oms.entity.OrderReturnApply;
import com.ywc.ymall.oms.entity.OrderSetting;
import com.ywc.ymall.oms.mapper.OrderReturnApplyMapper;
import com.ywc.ymall.oms.service.OrderReturnApplyService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.OrderReturnApplyParam;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper, OrderReturnApply> implements OrderReturnApplyService {

    @Override
    public PageInfoVo OrderReturnApply(OrderReturnApplyParam orderReturnApplyParam, Integer pageSize, Integer pageNum) {
        Page<OrderReturnApply> page = new Page<>(pageNum, pageSize);
        QueryWrapper<OrderReturnApply> wrapper = new QueryWrapper<>();
        //服务单号
        if(orderReturnApplyParam.getId()!=null){
            wrapper.eq("id",orderReturnApplyParam.getId());
        }
        //处理状态
        if(orderReturnApplyParam.getStatus()!=null){
            wrapper.eq("status",orderReturnApplyParam.getStatus());
        }
        //操作人员
        if(!StringUtils.isEmpty(orderReturnApplyParam.getHandleMan())){
            wrapper.like("handle_man",orderReturnApplyParam.getHandleMan());
        }
        //处理时间
        if(!StringUtils.isEmpty(orderReturnApplyParam.getHandleTime())) {
            wrapper.apply(!StringUtils.isEmpty(orderReturnApplyParam.getHandleTime()),"DATE_FORMAT( handle_time, '%Y-%m-%d' ) " +
                    " >= DATE_FORMAT( '"+orderReturnApplyParam.getHandleTime()+"', '%Y-%m-%d' )");
        }
        //申请时间
        if(!StringUtils.isEmpty(orderReturnApplyParam.getCreateTime())) {
            wrapper.apply(!StringUtils.isEmpty(orderReturnApplyParam.getCreateTime()),"DATE_FORMAT( create_time, '%Y-%m-%d' ) " +
                    " >= DATE_FORMAT( '"+orderReturnApplyParam.getCreateTime()+"', '%Y-%m-%d' )");
        }
        IPage<OrderReturnApply> selectPage = this.baseMapper.selectPage(page, wrapper);
        PageInfoVo pageInfoVo = new PageInfoVo(selectPage.getTotal(),selectPage.getPages(),selectPage.getSize(),
                selectPage.getRecords(),selectPage.getCurrent());
        return pageInfoVo;
    }

    @Override
    public OrderReturnApply queryOrderReturnApplyById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateStatus(Long id, OrderReturnApply orderReturnApply) {
        orderReturnApply.setId(id);
        orderReturnApply.setHandleTime(new Date());
        this.baseMapper.updateById(orderReturnApply);
    }

    @Override
    public void delete(List<Long> ids) {
        for (Long id : ids) {
            this.baseMapper.deleteById(id);
        }
    }
}
