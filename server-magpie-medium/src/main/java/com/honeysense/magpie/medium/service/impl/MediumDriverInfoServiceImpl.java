package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumDriverInfo;
import com.honeysense.magpie.medium.repository.MediumDriverInfoRepository;
import com.honeysense.magpie.medium.service.MediumDriverInfoService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumDriverInfoServiceImpl extends MagpieAppUserOneServiceImpl<MediumDriverInfo> implements MediumDriverInfoService {
    private final MediumDriverInfoRepository mediumDriverInfoRepository;

    @Autowired
    protected MediumDriverInfoServiceImpl(MediumDriverInfoRepository mediumDriverInfoRepository) {
        super(mediumDriverInfoRepository);

        this.mediumDriverInfoRepository = mediumDriverInfoRepository;
    }
}
