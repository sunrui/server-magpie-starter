package com.honeysense.magpie.user.entity;

import com.honeysense.magpie.user.entity.refer.UserRefer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@ApiModel(value = "用户邀请关系实例")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRelation extends UserRefer {
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    @Column(unique = true)
    private Long userId;
    @ApiModelProperty(value="直推邀请他的用户 ID", required = true)
    private Long directInvitorUserId;
    @ApiModelProperty(value="间推邀请他的用户 ID", required = true)
    private Long indirectInvitorUserId;

    public UserRelation(UserRefer userRefer) {
        this.setChannelId(userRefer.getChannelId());
        this.setDevice(userRefer.getDevice());
        this.setDeviceImei(userRefer.getDeviceImei());
        this.setDeviceVersion(userRefer.getDeviceVersion());
        this.setIp(userRefer.getIp());
        this.setUserAgent(userRefer.getUserAgent());
    }
}
