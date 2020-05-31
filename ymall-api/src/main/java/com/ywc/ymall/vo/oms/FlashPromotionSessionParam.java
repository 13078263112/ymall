package com.ywc.ymall.vo.oms;

import com.ywc.ymall.sms.entity.FlashPromotionSession;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 嘟嘟~
 * @date 2020/5/31 23:07
 */
@Data
public class FlashPromotionSessionParam extends FlashPromotionSession implements Serializable {
    private  Integer productCount;
}
