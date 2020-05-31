package com.ywc.ymall.sms.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ywc.ymall.sms.entity.Coupon;
import com.ywc.ymall.sms.entity.CouponProductCategoryRelation;
import com.ywc.ymall.sms.entity.CouponProductRelation;
import com.ywc.ymall.sms.mapper.CouponMapper;
import com.ywc.ymall.sms.service.CouponProductCategoryRelationService;
import com.ywc.ymall.sms.service.CouponProductRelationService;
import com.ywc.ymall.sms.service.CouponService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.SmsCouponParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 优惠卷表 服务实现类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
@Service
@Component
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Reference
    CouponProductRelationService couponProductRelationService;
    @Reference
    CouponProductCategoryRelationService couponProductCategoryRelationService;
    @Override
    public PageInfoVo listForPage(String name, Integer type, Integer pageSize, Integer pageNum) {
        QueryWrapper<Coupon> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(type!=null){
            wrapper.eq("type",type);
        }
        IPage<Coupon> iPage = this.baseMapper.selectPage(new Page<Coupon>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(iPage,pageSize.longValue());
    }

    @Override
    public SmsCouponParam getCouponItemInfo(Long id) {
        Coupon coupon = this.baseMapper.selectById(id);
        SmsCouponParam smsCouponParam = new SmsCouponParam();
        BeanUtils.copyProperties(coupon,smsCouponParam);
        List<CouponProductRelation>  couponProductRelations=couponProductRelationService.selectByCoupondId(id);
        List<CouponProductCategoryRelation>  couponProductCategoryRelation= couponProductCategoryRelationService.selectByCoupondId(id);
        smsCouponParam.setProductRelationList(couponProductRelations);
        smsCouponParam.setProductCategoryRelationList(couponProductCategoryRelation);
        return smsCouponParam;
    }

    @Override
    public void create(SmsCouponParam couponParam) {
        List<CouponProductCategoryRelation> relationList = couponParam.getProductCategoryRelationList();
        List<CouponProductRelation> list = couponParam.getProductRelationList();
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponParam,coupon);
        this.baseMapper.insert(coupon);

        relationList.forEach((relation)->{
            relation.setCouponId(coupon.getId());
            couponProductCategoryRelationService.insert(relation);
        });

        list.forEach((relation)->{
            relation.setCouponId(coupon.getId());
            couponProductRelationService.insert(relation);
        });
    }

    @Override
    public void updateCouponInfos(Long id, SmsCouponParam couponParam) {
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponParam,coupon);
        coupon.setId(id);
         this.baseMapper.updateById(coupon);
    }

    @Override
    public void deleteById(Long id) {
        this.baseMapper.deleteById(id);
    }

}
