package com.honeysense.magpie.pay.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PayAccountConsist extends MagpieEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;
    @NotNull
    private BigDecimal balance;
}
