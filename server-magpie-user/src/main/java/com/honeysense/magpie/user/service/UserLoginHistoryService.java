package com.honeysense.magpie.user.service;

import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.saas.service.MagpieUserManyService;
import com.honeysense.magpie.user.entity.UserLoginHistory;

public interface UserLoginHistoryService extends MagpieUserManyService<UserLoginHistory> {
    int countAllByUserIdAndTypeAndDayAndSuccess(Long userId, MagpieToken.MagpieTokenType type, Integer day, Boolean success);
}
