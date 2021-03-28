package com.honeysense.magpie.sms.service.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.impl.MagpieChannelManyServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.utils.format.MagpieTimeFormat;
import com.honeysense.magpie.sms.entity.SmsCode;
import com.honeysense.magpie.sms.entity.SmsCodeType;
import com.honeysense.magpie.sms.repository.SmsCodeRepository;
import com.honeysense.magpie.sms.service.SmsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SmsCodeServiceImpl extends MagpieChannelManyServiceImpl<SmsCode> implements SmsCodeService {
    private final SmsCodeRepository smsCodeRepository;
    private final long MAX_VERIFY_TIMES = 3;

    @Autowired
    protected SmsCodeServiceImpl(SmsCodeRepository smsCodeRepository) {
        super(smsCodeRepository);

        this.smsCodeRepository = smsCodeRepository;
    }

    @Override
    public String generateRandomSmsCode() {
        StringBuilder smsCode = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            smsCode.append(new Random().nextInt(10));
        }

        return smsCode.toString();
    }

    @Override
    public boolean validPhoneSmsCode(String phone) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        Page<SmsCode> smsCodePage = smsCodeRepository.findAllByPhoneAndDay(phone, MagpieTimeFormat.makeToday(), PageRequest.of(0, 10));
        if (smsCodePage.getTotalElements() == 0) {
            return false;
        }

        List<String> validCodes = new ArrayList<>();
        for (SmsCode smsCode : smsCodePage.getContent()) {
            if (smsCode.getExpiredAt().before(new Date())) {
                continue;
            }

            if (smsCode.getVerifyTimes() >= MAX_VERIFY_TIMES) {
                continue;
            }

            validCodes.add(smsCode.getCode());
        }

        return validCodes.size() > 0;
    }

    @Override
    public boolean validPhoneAndSmsCodeAndSmsCodeType(String phone, String code, SmsCodeType smsCodeType) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        if (!MagpieValidator.smsCode(code)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "code");
        }

        Page<SmsCode> smsCodePage = smsCodeRepository.findAllByPhoneAndDay(phone, MagpieTimeFormat.makeToday(), PageRequest.of(0, SmsCode.MAX_SEND_PER_DAY));
        if (smsCodePage.getTotalElements() == 0) {
            return false;
        }

        List<SmsCode> validCodes = new ArrayList<>();
        for (SmsCode smsCode : smsCodePage.getContent()) {
            if (smsCode.getExpiredAt().before(new Date())) {
                continue;
            }

            if (smsCode.getVerifyTimes() >= MAX_VERIFY_TIMES) {
                continue;
            }

            validCodes.add(smsCode);
        }

        for (SmsCode oneCode : validCodes) {
            if (oneCode.getCode().contentEquals(code) && oneCode.getType() == smsCodeType) {
                return true;
            }
        }

        for (SmsCode smsCode : smsCodePage.getContent()) {
            if (smsCode.getExpiredAt().before(new Date())) {
                continue;
            }

            if (smsCode.getVerifyTimes() >= MAX_VERIFY_TIMES) {
                continue;
            }

            smsCode.setVerifyTimes(smsCode.getVerifyTimes() + 1);
            smsCodeRepository.save(smsCode);
        }

        return false;
    }

    @Override
    public int countAllByPhoneAndDay(String phone, Integer day) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        if (day == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "day");
        }

        // TODO 需要报错
        SmsCode smsCode = new SmsCode();
        smsCode.setCode("123456");
        smsCode.setPhone("13012341234");
        smsCode.setDay(20200101);
        smsCode.setIp("ip");
        smsCode.setUserAgent("ua");
        smsCode.setChannelId(1L);
        smsCode.setVerifyTimes(1);
        smsCode.setType(SmsCodeType.LOGIN);
        smsCode.setExpiredAt(new Date());
        smsCodeRepository.save(smsCode);

        return smsCodeRepository.countAllByPhoneAndDay(phone, day);
    }

    @Override
    public MagpiePage<SmsCode> findByPhoneAndDay(String phone, Integer day, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.phone(phone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "phone");
        }

        if (day == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "day");
        }

        MagpieValidator.object(magpiePageRequest);

        Page<SmsCode> elements = smsCodeRepository.findAllByPhoneAndDay(phone, day, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
