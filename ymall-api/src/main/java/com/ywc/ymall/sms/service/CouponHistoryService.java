package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.CouponHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

/**
 * <p>
 * 优惠券使用、领取历史表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface CouponHistoryService extends IService<CouponHistory> {

    PageInfoVo listCouponHistoryForPage(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
