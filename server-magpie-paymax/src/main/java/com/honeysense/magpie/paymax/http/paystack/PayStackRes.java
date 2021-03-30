package com.honeysense.magpie.paymax.http.paystack;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayStackRes<T> {
    private String status;
    private String message;
    private T data;
}
