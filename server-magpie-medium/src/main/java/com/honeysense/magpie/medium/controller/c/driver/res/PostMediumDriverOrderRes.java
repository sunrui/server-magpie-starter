package com.honeysense.magpie.medium.controller.c.driver.res;

import com.honeysense.magpie.framework.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "提交投放订单 - 返回")
@Getter
@Setter
@Builder
public class PostMediumDriverOrderRes extends MagpieObject {
    @ApiModelProperty(value = "已经有订单正在进行中")
    private Boolean driverOrderProcessing;
    @ApiModelProperty(value = "投放订单 ID")
    private Long driverOrderId;
}
