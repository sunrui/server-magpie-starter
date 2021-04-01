package com.honeysense.magpie.user.entity.refer;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "实例 - 用户 - 来源")
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class UserRefer extends MagpieEntity {
    @ApiModelProperty(value="邀请他的用户 ID")
    private Long directInvitorUserId;
    @ApiModelProperty(value="开发者 ID")
    private Long appId;
    @ApiModelProperty(value="设备")
    private String device;
    @ApiModelProperty(value="设备号")
    private String deviceUuid;
    @ApiModelProperty(value="设备版本")
    private String deviceVersion;
    @ApiModelProperty(value="ip 地址", required = true)
    @NotBlank
    private String ip;
    @ApiModelProperty(value="用户代理", required = true)
    @NotBlank
    @Column(length = 1024)
    private String userAgent;
}
