package com.honeysense.magpie.paymax.service.account.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.repository.account.AccountTransactionRepository;
import com.honeysense.magpie.paymax.service.account.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    @Override
    public MagpiePage<AccountTransaction> findAllByAppId(Long appId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        MagpieValidator.object(magpiePageRequest);

        return new MagpiePage<>(accountTransactionRepository.findAllByAppId(appId, magpiePageRequest.of()));
    }
}
