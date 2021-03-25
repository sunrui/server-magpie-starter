package com.honeysense.media.service.impl;

import com.honeysense.media.entity.DriverOrderVerify;
import com.honeysense.media.repository.DriverOrderCertifyRepository;
import com.honeysense.media.repository.DriverOrderRepository;
import com.honeysense.media.service.DriverOrderVerifyService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverOrderVerifyServiceImpl extends MagpieChannelUserManyServiceImpl<DriverOrderVerify> implements DriverOrderVerifyService {
    private final DriverOrderRepository driverOrderRepository;
    private final DriverOrderCertifyRepository driverOrderCertifyRepository;

    @Autowired
    protected DriverOrderVerifyServiceImpl(DriverOrderRepository driverOrderRepository,
                                           DriverOrderCertifyRepository driverOrderCertifyRepository) {
        super(driverOrderCertifyRepository);

        this.driverOrderRepository = driverOrderRepository;
        this.driverOrderCertifyRepository = driverOrderCertifyRepository;
    }
}
