package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;

public interface MagpieAppUserManyService<T extends MagpieEntity> extends MagpieAppManyService<T> {
    int countAllByAppIdAndUserId(Long appId, Long userId);
    MagpiePage<T> findAllByAppIdAndUserId(Long appId, Long userId, MagpiePageRequest magpiePageRequest);
}
