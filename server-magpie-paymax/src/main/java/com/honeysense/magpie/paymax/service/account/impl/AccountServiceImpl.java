package com.honeysense.magpie.paymax.service.account.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppOneServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.paymax.entity.account.Account;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.entity.account.AccountTransactionType;
import com.honeysense.magpie.paymax.entity.app.AppConfigType;
import com.honeysense.magpie.paymax.repository.account.AccountRepository;
import com.honeysense.magpie.paymax.repository.account.AccountTransactionRepository;
import com.honeysense.magpie.paymax.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl extends MagpieAppOneServiceImpl<Account> implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    protected AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public Account insertOne(Long appId, Long userId, AppConfigType appConfigType) {
        if (!MagpieValidator.longId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.longId(userId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "userId");
        }

        if (appConfigType == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appConfigType");
        }

        Account account = accountRepository.findByAppId(appId);
        if (account != null) {
            throw new MagpieException(MagpieException.Type.DUPLICATE, "appId");
        }

        account = new Account();
        account.setAppId(appId);
        account.setUserId(userId);
        account.setAppConfigType(appConfigType);
        account.setBalance(BigDecimal.ZERO);
        accountRepository.save(account);

        return account;
    }

    @Override
    public Account findByAppId(Long appId) {
        if (!MagpieValidator.longId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        return accountRepository.findByAppId(appId);
    }

    @Override
    public MagpiePage<Account> findAll(MagpiePageRequest magpiePageRequest) {
        if (magpiePageRequest == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "magpiePageRequest");
        }

        return new MagpiePage<>(accountRepository.findAll(magpiePageRequest.of()));
    }

    @Override
    public AccountTransaction insertAccountTransaction(Long appId, String appUserId, AccountTransactionType type, String userId, BigDecimal amount,
                                                       String payload, String comment) {
        if (!MagpieValidator.longId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (type == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "type");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "amount");
        }

        Account account = accountRepository.findByAppId(appId);
        if (account == null) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        if (type == AccountTransactionType.WITHDRAW) {
            if (account.getBalance().compareTo(amount) < 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("appId", appId);
                map.put("type", type);
                map.put("userId", userId);
                map.put("account.balance", account.getBalance());
                map.put("amount", amount);

                throw new MagpieException(MagpieException.Type.BAD_LOGIC, map);
            }
        }

        AccountTransaction accountTransaction = new AccountTransaction();
        accountTransaction.setAppId(account.getAppId());
        accountTransaction.setAppUserId(appUserId);
        accountTransaction.setType(type);
        accountTransaction.setAccount(account);
        accountTransaction.setBalance(amount);
        accountTransaction.setBeforeBalance(account.getBalance());
        accountTransaction.setComment(comment);
        accountTransaction.setAfterBalance(
                type == AccountTransactionType.CHARGE ?
                        account.getBalance().add(amount) :
                        account.getBalance().subtract(amount)
        );

        accountTransaction = accountTransactionRepository.save(accountTransaction);
        return accountTransaction;
    }
}
