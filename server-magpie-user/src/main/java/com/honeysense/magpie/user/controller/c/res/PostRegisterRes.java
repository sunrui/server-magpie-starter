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
    @ApiModelProperty(value = "用户对象")
    private User user;
    @ApiModelProperty(value = "用户 ID 已存在")
    private Boolean userIdExists;
}
