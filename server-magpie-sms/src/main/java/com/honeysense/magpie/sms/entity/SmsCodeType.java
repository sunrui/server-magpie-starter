package com.honeysense.magpie.sms.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "验证码 - 类型")
public enum SmsCodeType {
    LOGIN, CHANGE_MOBILE
}
