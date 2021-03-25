package com.honeysense.magpie.medium.controller.c.driver.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "提交广告订单审核 - 请求")
@Getter
@Setter
public class PostMediumDriverOrderVerifyReq extends MagpieObject {
    @ApiModelProperty(value="司机提交的第 1 张认证照片", required = true)
    private String image1;
    @ApiModelProperty(value="司机提交的第 2 张认证照片", required = true)
    private Long image2;
    @ApiModelProperty(value="司机提交的第 3 张认证照片", required = true)
    private Long image3;
    @ApiModelProperty(value="备注信息", required = true)
    private String remark;
}
