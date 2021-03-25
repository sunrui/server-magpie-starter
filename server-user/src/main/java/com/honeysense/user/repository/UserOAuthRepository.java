package com.honeysense.user.repository;

import com.honeysense.magpie.saas.repository.MagpieRepository;
import com.honeysense.user.entity.UserOAuth;

import java.util.Optional;

public interface UserOAuthRepository extends MagpieRepository<UserOAuth> {
    Optional<UserOAuth> findByTypeAndAppIdAndOpenId(UserOAuth.Type openType, String appId, String openId);
}
