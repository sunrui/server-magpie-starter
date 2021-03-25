package com.honeysense.media.entity;

import com.honeysense.magpie.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "司机 - 基本信息 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfo extends MagpieEntity {
    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value="联系方式")
    private String contract;
    @ApiModelProperty(value="头像")
    private String avatar;
    @ApiModelProperty(value="网约车平台")
    private String carHailingPlatform;
    @ApiModelProperty(value="车牌号")
    private String plateNumber;
    @ApiModelProperty(value="车辆正面与司机照片")
    private String carImage;
    @ApiModelProperty(value="网约车证件照片")
    private String carHailingLicenseImage;
    @ApiModelProperty(value="是否已认证")
    private Boolean certified;
}
