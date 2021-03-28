package com.honeysense.magpie.user.repository;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.user.entity.UserThird;

import java.util.Optional;

public interface UserThirdRepository extends MagpieRepository<UserThird> {
    Optional<UserThird> findByTypeAndAppIdAndOpenId(UserThird.Type type, String appId, String openId);
}
