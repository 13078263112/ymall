package com.ywc.ymall.controller.ums.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

/**
 * @author 嘟嘟~
 * @date 2020/3/22 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminParam {
    @Length(min = 6,max = 18,message = "用户名长度必须是6-18位")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @NotEmpty
    @ApiModelProperty(value = "用户头像")
    private String icon;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "邮箱")
    private String email;

    //使用负责正则进行校验
    @Null  //必须是不传的
    @ApiModelProperty(value = "用户昵称")
    private String nickName;


    @ApiModelProperty(value = "备注")
    private String note;
}
