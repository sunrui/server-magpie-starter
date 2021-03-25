package com.honeysense.magpie.pay.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class PayTransaction extends MagpieEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private PayGateway payGateway;
    @NotNull
    private Integer toUserId;
    @NotNull
    @Min(0)
    private BigDecimal amount;
    @NotNull
    private Integer orderId;
    @NotBlank
    private String title;
}
