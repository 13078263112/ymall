package com.ywc.ymall.vo.oms;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 14:52
 */
@Data
public class OmsReceiverInfoParam implements Serializable {
    private Long orderId;
    private  String receiverName;
    private  String receiverPhone;
    private  String receiverPostCode;
    private  String receiverDetailAddress;
    private  String receiverProvince;
    private  String receiverCity;
    private  String receiverRegion;
    private  String deliveryCompany;
    private  String deliverySn;
    private  Integer status;
}
