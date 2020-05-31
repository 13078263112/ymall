package com.ywc.ymall.sms.service;

import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ywc.ymall.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 服务类
 * </p>
 *
 * @author 嘟嘟~
 * @since 2020-03-20
 */
public interface FlashPromotionProductRelationService extends IService<FlashPromotionProductRelation> {

    List<FlashPromotionProductRelation> selectByflashPromotionId(Long flashPromotionId);

    PageInfoVo listRelationForPage(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    void create(List<FlashPromotionProductRelation> relationList);

    void updateFlashPromotionProductRelationById(FlashPromotionProductRelation relation);

    void deleteById(Long id);
}
