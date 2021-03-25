package com.honeysense.user.controller.c.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "用户登录来源")
@Getter
@Setter
public class LoginRefer {
    @ApiModelProperty(value="邀请他的用户 ID")
    private Long directInvitorUserId;
    @ApiModelProperty(value="渠道 ID")
    private Long channelId;
    @ApiModelProperty(value="设备")
    private String device;
    @ApiModelProperty(value="设备号")
    private String deviceImei;
    @ApiModelProperty(value="设备版本")
    private String deviceVersion;
}
