package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.FlashPromotionSession;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;
import com.ywc.ymall.vo.oms.FlashPromotionSessionParam;

import java.util.List;

/**
 * <p>
 * 限时购场次表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface FlashPromotionSessionService extends IService<FlashPromotionSession> {

    List<FlashPromotionSession> getList();

    void create(FlashPromotionSession promotionSession);

    void updateStatus(Long id, Integer status);

    void updateFlashPromotionSessionById(FlashPromotionSession promotionSession);

    void deleteById(Long id);

    List<FlashPromotionSessionParam> selectListForPage(Long flashPromotionId);
}
