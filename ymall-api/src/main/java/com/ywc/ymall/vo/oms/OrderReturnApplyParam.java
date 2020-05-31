package com.ywc.ymall.vo.oms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 嘟嘟~
 * @date 2020/5/30 15:49
 */
@Data
public class OrderReturnApplyParam implements Serializable {
    @ApiModelProperty("服务单号")
    private  Long id;
    @ApiModelProperty("处理状态")
    private  Integer status;
    @ApiModelProperty("处理时间")
    private String handleTime;
    @ApiModelProperty("申请时间")
    private String createTime;
    @ApiModelProperty("操作人员")
    private String handleMan;
}
