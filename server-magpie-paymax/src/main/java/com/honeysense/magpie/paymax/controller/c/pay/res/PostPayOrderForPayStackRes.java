package com.honeysense.magpie.paymax.controller.c.pay.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPayOrderForPayStackRes {
    private Long payOrderId;
    private String authorizationUrl;
    private String reference;
}
