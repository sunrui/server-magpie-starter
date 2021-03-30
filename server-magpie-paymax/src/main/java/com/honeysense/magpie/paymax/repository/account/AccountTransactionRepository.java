package com.honeysense.magpie.paymax.repository.account;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountTransactionRepository extends MagpieRepository<AccountTransaction> {
    Page<AccountTransaction> findAllByAppId(Long appId, Pageable pageable);
}
