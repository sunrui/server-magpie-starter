package com.honeysense.magpie.paymax.entity.account;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account extends MagpieEntity {
    @NotNull
    private Integer appId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayType gatewayType;
    @NotNull
    @Min(0)
    private BigDecimal balance;
    @NotNull
    @Min(0)
    private BigDecimal freezeBalance;
    @NotNull
    @Min(0)
    private BigDecimal fee;
    @NotNull
    @OneToMany
    private Set<AccountUser> accountUsers;
    @NotNull
    @OneToMany
    private Set<AccountBill> accountBills;
}
