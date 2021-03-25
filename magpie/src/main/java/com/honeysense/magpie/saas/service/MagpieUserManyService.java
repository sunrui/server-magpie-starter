package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpiePage;

public interface MagpieUserManyService<T extends MagpieEntity> extends MagpieService<T> {
    int countAllByUserId(Long userId);
    MagpiePage<T> findAllByUserId(Long userId, int page, int size);
}
