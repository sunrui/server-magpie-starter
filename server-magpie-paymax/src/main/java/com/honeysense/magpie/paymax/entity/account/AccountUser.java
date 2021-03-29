package com.honeysense.magpie.paymax.entity.account;

import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class AccountUser extends MagpieEntity {
    @NotNull
    @JoinColumn
    @ManyToOne
    private Account account;
    @NotBlank
    private String userId;
    @NotNull
    @Min(0)
    private BigDecimal balance;
    @NotNull
    @Min(0)
    private BigDecimal freezeBalance;
    @NotNull
    @Min(0)
    private BigDecimal fee;
}
