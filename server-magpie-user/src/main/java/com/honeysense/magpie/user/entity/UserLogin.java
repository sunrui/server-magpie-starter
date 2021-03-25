package com.honeysense.magpie.user.entity;

import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "用户登录实例")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin extends UserRefer {
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="令牌类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MagpieToken.MagpieTokenType type;
    @ApiModelProperty(value="过期时间", required = true)
    @NotNull
    private Date expiredAt;

    public UserLogin(UserRefer userRefer) {
        this.setChannelId(userRefer.getChannelId());
        this.setDevice(userRefer.getDevice());
        this.setDeviceImei(userRefer.getDeviceImei());
        this.setDeviceVersion(userRefer.getDeviceVersion());
        this.setIp(userRefer.getIp());
        this.setUserAgent(userRefer.getUserAgent());
    }
}
