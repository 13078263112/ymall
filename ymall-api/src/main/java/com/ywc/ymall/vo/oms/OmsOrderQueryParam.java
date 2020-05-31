package com.ywc.ymall.vo.oms;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 13:15
 */
@Data
public class OmsOrderQueryParam  implements Serializable {
    @ApiModelProperty("订单编号")
    private  String orderSn;
    @ApiModelProperty("收货人模糊关键字")
    private  String   receiverKeyword;
    @ApiModelProperty("订单创建时间")
    private String createTime;
    @ApiModelProperty("订单状态")
    private Integer status;
    @ApiModelProperty("订单分类")
    private Integer orderType;
    @ApiModelProperty("订单来源")
    private Integer  sourceType;
}
