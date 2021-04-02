package com.honeysense.magpie.user.controller.c.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(value = "用户 - 登录 - 密码 - 请求")
@Getter
@Setter
public class PostRegisterReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;

    @ApiModelProperty(value = "手机号", example = "15012341234", required = true)
    @Length(min = 11, max = 11)
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value = "用户名", example = "userName", required = true)
    @NotBlank
    @Length(min = 4, max = 16)
    private String userName;
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;
    @ApiModelProperty(value = "魔术码", example = "magpie", required = true)
    @NotBlank
    @Length(min = 2, max = 20)
    private String magic;
}
