package com.honeysense.sms.service;

import com.honeysense.magpie.saas.service.MagpieChannelManyService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.sms.entity.SmsCode;
import com.honeysense.sms.entity.SmsCodeType;

public interface SmsCodeService extends MagpieChannelManyService<SmsCode> {
    String generateRandomSmsCode();

    boolean weatherHaveValidSmsCode(String phone);
    boolean weatherVerifyOk(String phone, String code, SmsCodeType smsCodeType);

    int countAllByPhoneAndDay(String phone, Long day);
    MagpiePage<SmsCode> findByPhoneAndDay(String phone, Long day, int page, int size);
}
