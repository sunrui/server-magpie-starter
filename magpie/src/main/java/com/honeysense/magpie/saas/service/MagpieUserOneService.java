package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;

public interface MagpieUserOneService<T extends MagpieEntity> extends MagpieService<T> {
    T findByUserId(Long userId);
}
