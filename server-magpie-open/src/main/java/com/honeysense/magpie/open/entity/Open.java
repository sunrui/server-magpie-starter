package com.honeysense.magpie.open.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Open extends MagpieEntity {
    @OneToOne
    private OpenStackPay openStackPay;
}
