package com.honeysense.magpie.sms.controller.req;

import com.honeysense.magpie.framework.entity.MagpieObject;
import com.honeysense.magpie.sms.entity.SmsCodeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@ApiModel(value = "手机验证码验证 - 请求")
@Getter
@Setter
public class PostSmsVerifyReq extends MagpieObject {
    @ApiModelProperty(value = "手机号", example = "15012341234", required = true)
    @NotBlank
    @Length(min = 11, max = 11)
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value = "验证码", example = "123456", required = true)
    @NotNull
    @Length(min = 6, max = 6)
    private String code;
    @ApiModelProperty(value = "验证码类型", example = "LOGIN", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SmsCodeType smsCodeType;
}
