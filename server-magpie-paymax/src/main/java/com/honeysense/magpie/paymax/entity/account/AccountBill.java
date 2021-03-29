package com.honeysense.magpie.paymax.entity.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class AccountBill extends MagpieEntity {
    @NotNull
    @JsonIgnore
    @ManyToOne
    private Account account;
}
