package com.honeysense.magpie.user.controller.c.req;

import com.honeysense.magpie.user.entity.refer.UserRefer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "用户 - 登录 - 来源")
@Getter
@Setter
public class LoginRefer {
    @ApiModelProperty(value = "邀请他的用户 ID")
    private Long directInvitorUserId;
    @ApiModelProperty(value = "开发者 ID")
    private Long appId;
    @ApiModelProperty(value = "设备")
    private String device;
    @ApiModelProperty(value = "设备号")
    private String deviceUuid;
    @ApiModelProperty(value = "设备版本")
    private String deviceVersion;

    public UserRefer toUserRefer(String ip, String userAgent) {
        UserRefer userRefer = new UserRefer();
        userRefer.setDirectInvitorUserId(directInvitorUserId);
        userRefer.setAppId(appId);
        userRefer.setDevice(device);
        userRefer.setDeviceUuid(deviceUuid);
        userRefer.setDeviceVersion(deviceVersion);
        userRefer.setIp(ip);
        userRefer.setUserAgent(userAgent);
        return userRefer;
    }
}
