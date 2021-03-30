package com.honeysense.magpie.paymax.service.account;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.paymax.entity.account.Account;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.entity.account.AccountTransactionType;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;

import java.math.BigDecimal;

public interface AccountService {
    Account insertOne(Long appId, Long userId, GatewayType gatewayType);
    Account findByAppId(Long appId);
    MagpiePage<Account> findAll(MagpiePageRequest magpiePageRequest);
    AccountTransaction insertAccountTransaction(Long appId, String appUserId, AccountTransactionType type, String userId, BigDecimal amount,
                                                String payload, String comment);
}
