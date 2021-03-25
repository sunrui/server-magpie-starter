package com.honeysense.media.service.impl;

import com.honeysense.media.entity.DriverInfo;
import com.honeysense.media.repository.DriverInfoRepository;
import com.honeysense.media.service.DriverInfoService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverInfoServiceImpl extends MagpieChannelUserOneServiceImpl<DriverInfo> implements DriverInfoService {
    private final DriverInfoRepository driverInfoRepository;

    @Autowired
    protected DriverInfoServiceImpl(DriverInfoRepository driverInfoRepository) {
        super(driverInfoRepository);

        this.driverInfoRepository = driverInfoRepository;
    }
}
