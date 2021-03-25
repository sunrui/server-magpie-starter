package com.honeysense.magpie.sms.service;

import com.honeysense.magpie.framework.saas.service.MagpieChannelManyService;
import com.honeysense.magpie.framework.entity.MagpiePage;
import com.honeysense.magpie.sms.entity.SmsCode;
import com.honeysense.magpie.sms.entity.SmsCodeType;

public interface SmsCodeService extends MagpieChannelManyService<SmsCode> {
    String generateRandomSmsCode();

    boolean weatherHaveValidSmsCode(String phone);
    boolean weatherVerifyOk(String phone, String code, SmsCodeType smsCodeType);

    int countAllByPhoneAndDay(String phone, Long day);
    MagpiePage<SmsCode> findByPhoneAndDay(String phone, Long day, int page, int size);
}
