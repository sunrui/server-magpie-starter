package com.honeysense.magpie.paymax.http.paystack.initialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPayStackInitializeRes {
    private String authorization_url;
    private String access_code;
    private String reference;
}
