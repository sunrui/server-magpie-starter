package com.honeysense.magpie.sms.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieChannelManyRepository;
import com.honeysense.magpie.sms.entity.SmsCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SmsCodeRepository extends MagpieChannelManyRepository<SmsCode> {
    int countAllByPhoneAndDay(String phone, Long day);
    Page<SmsCode> findAllByPhoneAndDay(String phone, Long day, Pageable pageable);
}
