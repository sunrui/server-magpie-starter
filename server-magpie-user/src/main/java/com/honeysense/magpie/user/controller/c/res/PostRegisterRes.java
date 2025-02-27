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
public class PostRegisterRes extends MagpieObject {
    @ApiModelProperty(value = "用户名称已存在")
    private Boolean userNameExists;
    @ApiModelProperty(value = "手机号已存在")
    private Boolean phoneExists;
    @ApiModelProperty(value = "直推用户 ID 不存在")
    private Boolean directInvitorUserIdNotExists;
    @ApiModelProperty(value = "魔法值不正确")
    private Boolean magicError;
    @ApiModelProperty(value = "用户对象")
    private User user;
}
