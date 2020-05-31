package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.CouponProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券和产品的关系表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface CouponProductRelationService extends IService<CouponProductRelation> {


    List<CouponProductRelation> selectByCoupondId(Long id);

    void insert(CouponProductRelation relation);
}