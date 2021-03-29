package com.honeysense.magpie.user.entity;

import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel(value = "实例 - 用户 - 登录记录")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginHistory extends UserRefer {
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="令牌类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MagpieToken.MagpieTokenType type;
    @ApiModelProperty(value="天数", required = true)
    @NotNull
    @Min(20210101)
    @Max((20991231))
    private Integer day;
    @ApiModelProperty(value="过期时间", required = true)
    @NotNull
    private Date expiredAt;
    @ApiModelProperty(value="是否成功", required = true)
    @NotNull
    private Boolean success;

    public UserLoginHistory(UserRefer userRefer) {
        this.setChannelId(userRefer.getChannelId());
        this.setDevice(userRefer.getDevice());
        this.setdeviceUuid(userRefer.getdeviceUuid());
        this.setDeviceVersion(userRefer.getDeviceVersion());
        this.setIp(userRefer.getIp());
        this.setUserAgent(userRefer.getUserAgent());
    }

    public void makeToday() {
        day = Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date()));
    }
}
