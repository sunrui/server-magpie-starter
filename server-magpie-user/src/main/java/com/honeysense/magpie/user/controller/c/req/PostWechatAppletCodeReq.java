package com.honeysense.magpie.user.controller.c.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "用户 - 登录 - 微信小程序 - 获取 CODE - 请求")
@Getter
@Setter
public class PostWechatAppletCodeReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;
    @ApiModelProperty(value = "code", required = true)
    @NotBlank
    private String code;
}
