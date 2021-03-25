package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import com.honeysense.magpie.framework.entity.MagpiePage;

public interface MagpieUserManyService<T extends MagpieEntity> extends MagpieService<T> {
    int countAllByUserId(Long userId);
    MagpiePage<T> findAllByUserId(Long userId, int page, int size);
}
