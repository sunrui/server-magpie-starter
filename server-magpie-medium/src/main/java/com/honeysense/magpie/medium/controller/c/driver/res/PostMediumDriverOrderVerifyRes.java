package com.honeysense.magpie.medium.controller.c.driver.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "提交投放订单审核 - 返回")
@Getter
@Setter
@Builder
public class PostMediumDriverOrderVerifyRes extends MagpieObject {
    @ApiModelProperty(value = "司机接受投放的广告订单 Id 不存在")
    private Boolean driverOrderIdNotExists;
    @ApiModelProperty(value = "状态机状态不对")
    private Boolean stateError;
    @ApiModelProperty(value = "投放订单审核 ID")
    private Long driverOrderVerifyId;
}
