package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.saas.repository.MagpieUserManyRepository;
import com.honeysense.magpie.user.entity.UserLoginHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserLoginHistoryRepository extends MagpieUserManyRepository<UserLoginHistory> {
    int countAllByUserIdAndTypeAndDayAndSuccess(Long userId, MagpieToken.MagpieTokenType type, Integer day, Boolean success);
    Page<UserLoginHistory> findAllByUserId(Long userId, Pageable pageable);
}
