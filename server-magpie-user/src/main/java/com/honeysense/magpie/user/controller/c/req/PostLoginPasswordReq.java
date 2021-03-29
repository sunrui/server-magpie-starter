package com.honeysense.magpie.user.controller.c.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@ApiModel(value = "用户 - 登录 - 密码 - 请求")
@Getter
@Setter
public class PostLoginPasswordReq extends MagpieObject {
    @ApiModelProperty(value = "用户来源", required = true)
    @NotNull
    private LoginRefer refer;

    @ApiModelProperty(value = "用户名", example = "userName", required = true)
    @NotBlank
    @Length(min = 4, max = 16)
    private String userName;
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @NotBlank
    @Length(min = 6, max = 20)
    private String password;
    @ApiModelProperty(value = "最大过期时间（毫秒）", example = "604800000", required = true)
    @NotNull
    @Min(1)
    private Integer maxAge;
}
