package com.honeysense.magpie.pay.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
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
public class PayAccountOpenConsist extends MagpieEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private PayAccountChancel payAccountChancel;
    @NotNull
    private BigDecimal balance;
}
