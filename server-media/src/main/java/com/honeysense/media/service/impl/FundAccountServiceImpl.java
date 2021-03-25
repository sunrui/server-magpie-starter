package com.honeysense.media.service.impl;

import com.honeysense.media.entity.FundAccount;
import com.honeysense.media.repository.FundAccountRepository;
import com.honeysense.media.service.FundAccountService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundAccountServiceImpl extends MagpieChannelUserOneServiceImpl<FundAccount> implements FundAccountService {
    private final FundAccountRepository fundAccountRepository;

    @Autowired
    protected FundAccountServiceImpl(FundAccountRepository fundAccountRepository) {
        super(fundAccountRepository);

        this.fundAccountRepository = fundAccountRepository;
    }
}
