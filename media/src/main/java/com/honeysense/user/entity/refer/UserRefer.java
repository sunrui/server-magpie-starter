package com.honeysense.user.entity.refer;

import com.honeysense.magpie.entity.MagpieEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class UserRefer extends MagpieEntity {
    @ApiModelProperty(value="渠道 ID")
    private Long channelId;
    @ApiModelProperty(value="设备")
    private String device;
    @ApiModelProperty(value="设备号")
    private String deviceImei;
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
