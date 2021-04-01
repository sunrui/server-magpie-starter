package com.honeysense.magpie.paymax.entity.pay;

import com.honeysense.magpie.framework.saas.entity.MagpieAppUserManyEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class PayOrder extends MagpieAppUserManyEntity {
    @NotNull
    private GatewayType gatewayType;
    @NotBlank
    private String gatewayId;
    private String payload;
    @NotNull
    @Min(0)
    private BigDecimal amount;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PayOrderStatus status;
    @NotNull
    @Min(0)
    private Date expiredAt;
    @Enumerated(EnumType.STRING)
    private PayOrderMethod method;
    private Date paidAt;
    private String appUserId;
    private String comment;
}
