package com.honeysense.magpie.user.controller.c.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import com.honeysense.magpie.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "用户 - 登录 - 密码 - 返回")
@Getter
@Setter
@Builder
public class PostLoginPasswordRes extends MagpieObject {
    @ApiModelProperty(value = "用户对象")
    private User user;
    @ApiModelProperty(value = "直推用户 ID 不存在")
    private Boolean directInvitorUserIdNotExists;
    @ApiModelProperty(value = "用户名不存在")
    private Boolean userNameNotExists;
    @ApiModelProperty(value = "密码校验错误")
    private Boolean passwordVerifyError;
    @ApiModelProperty(value = "密码被锁定")
    private Boolean passwordLocked;
    @ApiModelProperty(value = "登录过频繁")
    private Boolean frequently;
}
