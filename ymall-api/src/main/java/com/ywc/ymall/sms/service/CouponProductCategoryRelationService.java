package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.CouponProductCategoryRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券和产品分类关系表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface CouponProductCategoryRelationService extends IService<CouponProductCategoryRelation> {

    List<CouponProductCategoryRelation> selectByCoupondId(Long id);

    void insert(CouponProductCategoryRelation relation);
}
