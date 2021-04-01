package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;

public interface MagpieAppUserOneService<T extends MagpieEntity> extends MagpieAppManyService<T> {
    T findByAppIdAndUserId(Long appId, Long userId);
}
