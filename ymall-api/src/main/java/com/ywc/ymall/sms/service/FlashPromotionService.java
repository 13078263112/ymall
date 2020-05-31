package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.FlashPromotion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface FlashPromotionService extends IService<FlashPromotion> {

    PageInfoVo listflashPromotionForPage(String keyword, Integer pageSize, Integer pageNum);

    void createFlashPromotion(FlashPromotion flashPromotion);

    void deleteById(Long id);

    void updateStatus(Long id, Integer status);
}
