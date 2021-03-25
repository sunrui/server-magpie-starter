package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "资金账户 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumFundAccount extends MagpieEntity {
    /**
     * 账户类型
     */
    public enum Type {
        /**
         * 平台
         */
        PLATFORM,
        /**
         * 广告主
         */
        SPONSOR,
        /**
         * 司机
         */
        DRIVER
    }

    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="账户类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    public Type type;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="账户金额", required = true)
    @Min(0)
    @NotNull
    private Long amount;
    @ApiModelProperty(value="冻结入金额", required = true)
    @NotNull
    @Min(0)
    private Long freezeInAmount;
    @ApiModelProperty(value="冻结出金额", required = true)
    @NotNull
    private Long freezeOutAmount;
}
