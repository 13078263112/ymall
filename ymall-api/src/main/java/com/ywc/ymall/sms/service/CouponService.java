package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.SmsCouponParam;

/**
 * <p>
 * 优惠卷表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface CouponService extends IService<Coupon> {

    PageInfoVo listForPage(String name, Integer type, Integer pageSize, Integer pageNum);

    SmsCouponParam getCouponItemInfo(Long id);

    void create(SmsCouponParam couponParam);

    void updateCouponInfos(Long id, SmsCouponParam couponParam);

    void deleteById(Long id);
}
