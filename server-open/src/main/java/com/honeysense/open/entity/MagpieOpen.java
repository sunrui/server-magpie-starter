package com.honeysense.open.entity;

import com.honeysense.magpie.entity.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class MagpieOpen extends MagpieEntity {
    @OneToOne
    private MagpieOpenStackPay magpieOpenStackPay;
}
