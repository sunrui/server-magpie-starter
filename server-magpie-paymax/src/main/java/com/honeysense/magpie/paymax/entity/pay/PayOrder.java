package com.honeysense.magpie.paymax.entity.pay;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PayOrder extends MagpieEntity {
    @NotNull
    private GatewayType gatewayType;
    @NotNull
    private String customerId;
    @NotBlank
    private String payload;
    @NotNull
    @Min(0)
    private BigDecimal amount;
}
