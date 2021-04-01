package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@ApiModel(value = "资金账户 - 变动历史 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumFundTradeHistory extends MagpieEntity {
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

    /**
     * 资金走向
     */
    public enum Transfer {
        IN, OUT, FREEZE_IN, FREEZE_OUT
    }

    @ApiModelProperty(value="开发者 ID", required = true)
    @NotNull
    private Long appId;
    @ApiModelProperty(value="账户类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;
    @ApiModelProperty(value="资金走向", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Transfer transfer;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="从哪个用户 ID", required = true)
    @NotNull
    private Long fromUserId;
    @ApiModelProperty(value="发生的金额", required = true)
    @NotNull
    private Integer transferAmount;
    @ApiModelProperty(value="现在的资金", required = true)
    @NotNull
    private Integer nowAmount;
    @ApiModelProperty(value="现在的冻结资金", required = true)
    @NotNull
    private Integer nowFreezeAmount;
    @ApiModelProperty(value="备注信息", required = true)
    private String remark;
}
