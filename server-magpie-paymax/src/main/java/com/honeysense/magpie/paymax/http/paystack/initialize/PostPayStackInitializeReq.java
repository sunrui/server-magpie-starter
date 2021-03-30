package com.honeysense.magpie.paymax.http.paystack.initialize;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PostPayStackInitializeReq {
    private String email;
    private BigDecimal amount;
}
