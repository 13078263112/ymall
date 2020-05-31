package com.ywc.ymall.vo.oms;

import com.ywc.ymall.pms.entity.Product;
import com.ywc.ymall.sms.entity.FlashPromotionProductRelation;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 23:32
 */
@Data
public class FlashPromotionProductRelationParam extends FlashPromotionProductRelation implements Serializable {
    private Product product;
}
