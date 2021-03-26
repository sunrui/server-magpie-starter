package com.honeysense.magpie.user.controller.c.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "用户 - 登录 - 微信小程序 - 获取 CODE - 返回")
@Getter
@Setter
@Builder
public class PostWechatAppletCodeRes extends MagpieObject {
    private String openid;
    private String session_key;
}
