package com.honeysense.magpie.user.controller.c.req;

import com.honeysense.magpie.framework.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "微信小程序登录获取手机号 - 请求")
@Getter
@Setter
public class PostWechatAppletMobileReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;
    @ApiModelProperty(value = "encryptedData", required = true)
    @NotBlank
    private String encryptedData;
    @ApiModelProperty(value = "sessionKey", required = true)
    @NotBlank
    private String sessionKey;
    @ApiModelProperty(value = "iv", required = true)
    @NotBlank
    private String iv;
}
