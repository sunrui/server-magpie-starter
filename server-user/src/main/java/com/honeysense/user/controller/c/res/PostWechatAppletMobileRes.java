package com.honeysense.user.controller.c.res;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "微信小程序登录获取手机号 - 返回")
@Getter
@Setter
@Builder
public class PostWechatAppletMobileRes extends MagpieObject {
    private String mobile;
}
