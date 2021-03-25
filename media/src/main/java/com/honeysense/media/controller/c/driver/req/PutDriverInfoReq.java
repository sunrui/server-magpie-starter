package com.honeysense.media.controller.c.driver.req;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "更新司机信息 - 请求")
@Getter
@Setter
public class PutDriverInfoReq extends MagpieObject {
    @ApiModelProperty(value = "司机名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value = "联系方式")
    private String contract;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "网约车平台")
    private String carHailingPlatform;
    @ApiModelProperty(value = "司机车牌号")
    private String plateNumber;
    @ApiModelProperty(value = "车辆正面与司机照片")
    private String carImage;
    @ApiModelProperty(value = "司机网约车证件照片")
    private String carHailingLicenseImage;
}
