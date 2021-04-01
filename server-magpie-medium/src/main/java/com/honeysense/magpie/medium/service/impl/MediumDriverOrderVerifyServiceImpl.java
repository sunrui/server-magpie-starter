package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumDriverOrderVerify;
import com.honeysense.magpie.medium.repository.MediumDriverOrderCertifyRepository;
import com.honeysense.magpie.medium.repository.MediumDriverOrderRepository;
import com.honeysense.magpie.medium.service.MediumDriverOrderVerifyService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumDriverOrderVerifyServiceImpl extends MagpieAppUserManyServiceImpl<MediumDriverOrderVerify> implements MediumDriverOrderVerifyService {
    private final MediumDriverOrderRepository mediumDriverOrderRepository;
    private final MediumDriverOrderCertifyRepository mediumDriverOrderCertifyRepository;

    @Autowired
    protected MediumDriverOrderVerifyServiceImpl(MediumDriverOrderRepository mediumDriverOrderRepository,
                                                 MediumDriverOrderCertifyRepository mediumDriverOrderCertifyRepository) {
        super(mediumDriverOrderCertifyRepository);

        this.mediumDriverOrderRepository = mediumDriverOrderRepository;
        this.mediumDriverOrderCertifyRepository = mediumDriverOrderCertifyRepository;
    }
}
