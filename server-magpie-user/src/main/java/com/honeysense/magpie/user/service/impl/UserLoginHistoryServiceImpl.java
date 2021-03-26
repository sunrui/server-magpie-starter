package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieUserManyServiceImpl;
import com.honeysense.magpie.user.entity.UserLoginHistory;
import com.honeysense.magpie.user.repository.UserLoginHistoryRepository;
import com.honeysense.magpie.user.service.UserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginHistoryServiceImpl extends MagpieUserManyServiceImpl<UserLoginHistory> implements UserLoginHistoryService {
    private final UserLoginHistoryRepository userLoginHistoryRepository;

    @Autowired
    protected UserLoginHistoryServiceImpl(UserLoginHistoryRepository userLoginHistoryRepository) {
        super(userLoginHistoryRepository);

        this.userLoginHistoryRepository = userLoginHistoryRepository;
    }
}
