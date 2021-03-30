package com.honeysense.magpie.paymax.controller.c.pay.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class PostPayOrderForPayStackReq extends PostPayOrderReq {
    @NotBlank
    private String email;
    @NotBlank
    private BigDecimal amount;
}
