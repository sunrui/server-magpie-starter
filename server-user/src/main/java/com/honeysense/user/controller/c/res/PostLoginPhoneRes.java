package com.honeysense.user.controller.c.res;

import com.honeysense.magpie.entity.MagpieObject;
import com.honeysense.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "手机登录 - 返回")
@Getter
@Setter
@Builder
public class PostLoginPhoneRes extends MagpieObject {
    @ApiModelProperty(value = "用户对象")
    private User user;
    @ApiModelProperty(value = "直推用户 ID 不存在")
    private Boolean directInvitorUserIdNotExists;
    @ApiModelProperty(value = "需要发送短信验证码")
    private Boolean smsCodeSendNeeded;
    @ApiModelProperty(value = "短信验证码校验错误")
    private Boolean smsCodeVerifyError;
}
