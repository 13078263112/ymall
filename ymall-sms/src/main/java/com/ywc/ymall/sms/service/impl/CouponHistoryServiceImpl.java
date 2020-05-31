package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.CouponHistory;
import com.ywc.ymall.sms.mapper.CouponHistoryMapper;
import com.ywc.ymall.sms.service.CouponHistoryService;
import com.ywc.ymall.vo.PageInfoVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 优惠券使用、领取历史表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class CouponHistoryServiceImpl extends ServiceImpl<CouponHistoryMapper, CouponHistory> implements CouponHistoryService {
    @Override
    public PageInfoVo listCouponHistoryForPage(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        QueryWrapper<CouponHistory> wrapper = new QueryWrapper<>();
        if(couponId!=null){
            wrapper.eq("coupon_id",couponId);
        }
        if(useStatus!=null){
            wrapper.eq("use_status",useStatus);
        }
        if(!StringUtils.isEmpty(orderSn)){
            wrapper.like("order_sn",orderSn);
        }
        IPage<CouponHistory> iPage = this.baseMapper.selectPage(new Page<CouponHistory>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }
}
