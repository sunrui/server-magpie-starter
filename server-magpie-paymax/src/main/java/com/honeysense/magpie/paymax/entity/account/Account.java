package com.honeysense.magpie.paymax.entity.account;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account extends MagpieEntity {
    @NotNull
    @Column(unique = true)
    private Long appId;
    @NotBlank
    private Long userId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayType gatewayType;
    @NotNull
    @Min(0)
    private BigDecimal balance;
    @NotNull
    @OneToMany
    private Set<AccountTransaction> accountTransactions;
}
