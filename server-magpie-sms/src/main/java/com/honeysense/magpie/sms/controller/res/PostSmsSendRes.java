package com.honeysense.magpie.sms.controller.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "验证码 - 手机登录 - 返回")
@Getter
@Setter
@Builder
public class PostSmsSendRes extends MagpieObject {
    @ApiModelProperty(value = "超过最大发送次数", example = "true")
    private Boolean maxSendReached;
    @ApiModelProperty(value = "发送成功", example = "false")
    private Boolean success;
    @ApiModelProperty(value = "验证码(正式环境不返回)", example = "123456")
    private String smsCode;
}
