package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;

public interface MagpieAppService<T extends MagpieEntity> extends MagpieService<T> {
    T findByAppIdAndId(Long appId, Long id);
    void updateByAppIdAndId(Long appId, Long id, T t);
    void deleteByAppIdAndId(Long appId, Long id);
}
