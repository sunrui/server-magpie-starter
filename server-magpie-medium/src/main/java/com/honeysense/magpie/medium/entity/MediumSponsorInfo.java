package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "广告主 - 入驻基本信息 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumSponsorInfo extends MagpieEntity {
    @ApiModelProperty(value="开发者 ID", required = true)
    @NotNull
    private Long appId;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value="logo")
    private String logo;
    @ApiModelProperty(value="slogan")
    private String slogan;
    @ApiModelProperty(value="公司")
    private String corp;
    @ApiModelProperty(value="联系方式")
    private String contract;
    @ApiModelProperty(value="办公地址")
    private String address;
    @ApiModelProperty(value="其它信息")
    private String detail;
    @ApiModelProperty(value="是否已认证", required = true)
    private Boolean certified;
}
