package com.honeysense.magpie.user.service.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieUserManyServiceImpl;
import com.honeysense.magpie.user.entity.UserLogin;
import com.honeysense.magpie.user.repository.UserLoginRepository;
import com.honeysense.magpie.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl extends MagpieUserManyServiceImpl<UserLogin> implements UserLoginService {
    private final UserLoginRepository userLoginRepository;

    @Autowired
    protected UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        super(userLoginRepository);

        this.userLoginRepository = userLoginRepository;
    }
}
