package com.honeysense.magpie.paymax.service.account;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;

public interface AccountTransactionService {
    MagpiePage<AccountTransaction> findAllByAppId(Long appId, MagpiePageRequest magpiePageRequest);
}
