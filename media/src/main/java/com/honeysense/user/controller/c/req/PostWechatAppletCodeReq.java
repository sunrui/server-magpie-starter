package com.honeysense.user.controller.c.req;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "微信小程序登录获取 CODE - 请求")
@Getter
@Setter
public class PostWechatAppletCodeReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;
    @ApiModelProperty(value = "CODE ", required = true)
    @NotBlank
    private String code;
}
