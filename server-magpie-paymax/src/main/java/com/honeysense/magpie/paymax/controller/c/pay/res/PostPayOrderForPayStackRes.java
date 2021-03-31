package com.honeysense.magpie.paymax.controller.c.pay.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostPayOrderForPayStackRes {
    private Long payOrderId;
    private String payUrl;
}
