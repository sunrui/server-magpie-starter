package com.honeysense.magpie.paymax.service.account;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieAppOneService;
import com.honeysense.magpie.paymax.entity.account.Account;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.entity.account.AccountTransactionType;
import com.honeysense.magpie.paymax.entity.app.AppConfigType;

import java.math.BigDecimal;

public interface AccountService extends MagpieAppOneService<Account> {
    Account insertOne(Long appId, Long userId, AppConfigType appConfigType);
    MagpiePage<Account> findAll(MagpiePageRequest magpiePageRequest);
    AccountTransaction insertAccountTransaction(Long appId, String appUserId, AccountTransactionType type, String userId, BigDecimal amount,
                                                String payload, String comment);
}
