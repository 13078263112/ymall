package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.CouponProductCategoryRelation;
import com.ywc.ymall.sms.entity.CouponProductRelation;
import com.ywc.ymall.sms.mapper.CouponProductCategoryRelationMapper;
import com.ywc.ymall.sms.service.CouponProductCategoryRelationService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class CouponProductCategoryRelationServiceImpl extends ServiceImpl<CouponProductCategoryRelationMapper, CouponProductCategoryRelation> implements CouponProductCategoryRelationService {

    @Override
    public List<CouponProductCategoryRelation> selectByCoupondId(Long id) {
        QueryWrapper<CouponProductCategoryRelation> wrapper = new QueryWrapper<CouponProductCategoryRelation>().eq("coupon_id",id);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public void insert(CouponProductCategoryRelation relation) {
        this.baseMapper.insert(relation);
    }
}
