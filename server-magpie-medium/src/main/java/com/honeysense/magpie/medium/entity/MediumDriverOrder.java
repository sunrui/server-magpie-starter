package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "司机 - 接受投放的广告订单 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumDriverOrder extends MagpieEntity {
    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="司机用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="投放到司机的拍卖 ID", required = true)
    @NotNull
    private Long sponsorPublishId;
    @ApiModelProperty(value="订单更新状态", required = true)
    @OneToMany
    private List<MediumDriverOrderVerify> orderCertifies;
    @ApiModelProperty(value="物料邮寄地址", required = true)
    @NotBlank
    private String sendAddress;
    @ApiModelProperty(value="物料邮寄联系人", required = true)
    @NotBlank
    private String sendUser;
    @ApiModelProperty(value="物料邮寄联系手机", required = true)
    @NotBlank
    private String sendPhone;
    @ApiModelProperty(value="最新的订单状态", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MediumDriverOrderStatus status;
}
