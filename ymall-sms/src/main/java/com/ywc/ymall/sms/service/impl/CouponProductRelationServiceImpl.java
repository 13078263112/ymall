package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.CouponProductRelation;
import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import com.ywc.ymall.sms.mapper.CouponProductRelationMapper;
import com.ywc.ymall.sms.service.CouponProductRelationService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 优惠券和产品的关系表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class CouponProductRelationServiceImpl extends ServiceImpl<CouponProductRelationMapper, CouponProductRelation> implements CouponProductRelationService {

    @Override
    public List<CouponProductRelation> selectByCoupondId(Long id) {
        QueryWrapper<CouponProductRelation> wrapper = new QueryWrapper<CouponProductRelation>().eq("coupon_id",id);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void insert(CouponProductRelation relation) {
        this.baseMapper.insert(relation);
    }
}
