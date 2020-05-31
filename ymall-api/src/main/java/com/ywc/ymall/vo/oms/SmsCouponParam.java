package com.ywc.ymall.vo.oms;

import com.ywc.ymall.sms.entity.Coupon;
import com.ywc.ymall.sms.entity.CouponProductCategoryRelation;
import com.ywc.ymall.sms.entity.CouponProductRelation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 嘟嘟~
 * @date 2020/6/1 0:03
 */
@Data
public class SmsCouponParam extends Coupon implements Serializable {
    //优惠券绑定的商品
    private List<CouponProductRelation> productRelationList;
    //优惠券绑定的商品分类
    private List<CouponProductCategoryRelation> productCategoryRelationList;

}
