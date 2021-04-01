package com.honeysense.magpie.paymax.service.account.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieAppManyServiceImpl;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.repository.account.AccountTransactionRepository;
import com.honeysense.magpie.paymax.service.account.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionServiceImpl extends MagpieAppManyServiceImpl<AccountTransaction> implements AccountTransactionService {
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    protected AccountTransactionServiceImpl(AccountTransactionRepository accountTransactionRepository) {
        super(accountTransactionRepository);
    }
}
