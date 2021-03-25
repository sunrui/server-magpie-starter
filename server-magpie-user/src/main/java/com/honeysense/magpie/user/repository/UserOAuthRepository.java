package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.user.entity.UserOAuth;

import java.util.Optional;

public interface UserOAuthRepository extends MagpieRepository<UserOAuth> {
    Optional<UserOAuth> findByTypeAndAppIdAndOpenId(UserOAuth.Type openType, String appId, String openId);
}
