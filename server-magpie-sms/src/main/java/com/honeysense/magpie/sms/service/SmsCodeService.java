package com.honeysense.magpie.sms.service;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieChannelManyService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.sms.entity.SmsCode;
import com.honeysense.magpie.sms.entity.SmsCodeType;

public interface SmsCodeService extends MagpieChannelManyService<SmsCode> {
    String generateRandomSmsCode();

    boolean validPhoneSmsCode(String phone);
    boolean validPhoneAndSmsCodeAndSmsCodeType(String phone, String code, SmsCodeType smsCodeType);

    int countAllByPhoneAndDay(String phone, Integer day);
    MagpiePage<SmsCode> findByPhoneAndDay(String phone, Integer day, MagpiePageRequest magpiePageRequest);
}
