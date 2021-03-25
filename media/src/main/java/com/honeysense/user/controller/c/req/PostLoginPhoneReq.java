package com.honeysense.user.controller.c.req;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@ApiModel(value = "手机登录 - 请求")
@Getter
@Setter
public class PostLoginPhoneReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;

    @ApiModelProperty(value = "手机号", example = "15012341234", required = true)
    @NotBlank
    @Length(min = 11, max = 11)
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value = "验证码", example = "123456", required = true)
    @NotNull
    @Length(min = 6, max = 6)
    private String smsCode;
    @ApiModelProperty(value = "最大过期时间（毫秒）", example = "604800000", required = true)
    @NotNull
    @Min(1)
    private Integer maxAge;
}
