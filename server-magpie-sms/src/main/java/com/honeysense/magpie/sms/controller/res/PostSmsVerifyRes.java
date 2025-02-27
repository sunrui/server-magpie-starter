package com.honeysense.magpie.sms.controller.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "验证码 - 手机验证 - 返回")
@Getter
@Setter
@Builder
public class PostSmsVerifyRes extends MagpieObject {
    @ApiModelProperty(value = "验证码不存在", example = "true")
    private Boolean codeNotExists;
    @ApiModelProperty(value = "验证失败", example = "false")
    private Boolean verifyError;
    @ApiModelProperty(value = "验证成功", example = "false")
    private Boolean verifyOk;
}
