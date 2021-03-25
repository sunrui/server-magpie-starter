package com.honeysense.magpie.pay.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
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
    private PayGateway payGateway;
    @NotNull
    private String customerId;
    @NotBlank
    private String payload;
    @NotNull
    @Min(0)
    private BigDecimal amount;
}
