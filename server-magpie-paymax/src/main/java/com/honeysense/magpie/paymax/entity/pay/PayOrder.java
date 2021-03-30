package com.honeysense.magpie.paymax.entity.pay;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PayOrder extends MagpieEntity {
    @NotNull
    private Long appId;
    private String appUserId;
    @NotNull
    private GatewayType gatewayType;
    @NotNull
    private String gatewayId;
    private String payload;
    @NotNull
    @Min(0)
    private BigDecimal amount;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PayOrderStatus status;
}
