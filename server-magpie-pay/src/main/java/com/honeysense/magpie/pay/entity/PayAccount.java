package com.honeysense.magpie.pay.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PayAccount extends MagpieEntity {
    @NotNull
    private Integer openId;
    @NotNull
    @Min(0)
    private BigDecimal balance;
    @NotNull
    @Min(0)
    private BigDecimal freezeBalance;
}
