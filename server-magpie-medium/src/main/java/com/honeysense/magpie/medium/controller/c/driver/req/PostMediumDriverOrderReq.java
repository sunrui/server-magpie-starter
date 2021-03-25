package com.honeysense.magpie.medium.controller.c.driver.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "提交投放订单 - 请求")
@Getter
@Setter
public class PostMediumDriverOrderReq extends MagpieObject {
    /**
     * 投放到司机的拍卖
     */
    @NotNull
    private Long servingAuctionId;
    /**
     * 物料邮寄地址
     */
    @NotBlank
    private String sendAddress;
    /**
     * 物料邮寄联系人
     */
    @NotBlank
    private String sendUser;
    /**
     * 物料邮寄联系手机
     */
    @NotBlank
    private String sendPhone;
}
