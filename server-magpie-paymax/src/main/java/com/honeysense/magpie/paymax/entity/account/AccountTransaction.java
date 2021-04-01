package com.honeysense.magpie.paymax.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.saas.entity.MagpieAppManyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class AccountTransaction extends MagpieAppManyEntity {
    @NotNull
    @JsonIgnore
    @ManyToOne
    private Account account;
    private String appUserId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountTransactionType type;
    @NotNull
    @Min(0)
    private BigDecimal balance;
    @NotNull
    @Min(0)
    private BigDecimal fee;
    @NotNull
    @Min(0)
    private BigDecimal beforeBalance;
    @NotNull
    @Min(0)
    private BigDecimal afterBalance;
    private String comment;
}
