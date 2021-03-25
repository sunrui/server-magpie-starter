package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.entity.MagpieEntity;

public interface MagpieUserOneService<T extends MagpieEntity> extends MagpieService<T> {
    T findByUserId(Long userId);
}
