package com.honeysense.sms.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "短信验证码类型")
public enum SmsCodeType {
    LOGIN, CHANGE_MOBILE
}
