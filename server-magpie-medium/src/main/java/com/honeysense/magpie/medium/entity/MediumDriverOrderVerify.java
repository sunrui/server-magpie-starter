package com.honeysense.magpie.medium.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@ApiModel(value = "司机 - 提交认证审核 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumDriverOrderVerify extends MagpieEntity {
    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="司机用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="司机下单的投放广告订单", required = true)
    @NotNull
    @JsonIgnore
    @ManyToOne
    private MediumDriverOrder mediumDriverOrder;
    @ApiModelProperty(value="状态类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MediumDriverOrderStatus status;
    @ApiModelProperty(value="司机提交的第 1 张认证照片", required = true)
    private String image1;
    @ApiModelProperty(value="司机提交的第 2 张认证照片", required = true)
    private Long image2;
    @ApiModelProperty(value="司机提交的第 3 张认证照片", required = true)
    private Long image3;
    @ApiModelProperty(value="备注信息", required = true)
    private String remark;
}
