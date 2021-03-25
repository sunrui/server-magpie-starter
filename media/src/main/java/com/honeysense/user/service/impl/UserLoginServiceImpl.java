package com.honeysense.user.service.impl;

import com.honeysense.magpie.saas.service.impl.MagpieUserManyServiceImpl;
import com.honeysense.user.entity.UserLogin;
import com.honeysense.user.repository.UserLoginRepository;
import com.honeysense.user.service.UserLoginService;
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
