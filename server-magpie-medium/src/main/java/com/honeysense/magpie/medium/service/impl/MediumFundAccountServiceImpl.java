package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumFundAccount;
import com.honeysense.magpie.medium.repository.MediumFundAccountRepository;
import com.honeysense.magpie.medium.service.MediumFundAccountService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieChannelUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumFundAccountServiceImpl extends MagpieChannelUserOneServiceImpl<MediumFundAccount> implements MediumFundAccountService {
    private final MediumFundAccountRepository mediumFundAccountRepository;

    @Autowired
    protected MediumFundAccountServiceImpl(MediumFundAccountRepository mediumFundAccountRepository) {
        super(mediumFundAccountRepository);

        this.mediumFundAccountRepository = mediumFundAccountRepository;
    }
}
