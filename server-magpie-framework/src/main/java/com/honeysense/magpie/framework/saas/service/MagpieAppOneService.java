package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;

public interface MagpieAppOneService<T extends MagpieEntity> extends MagpieAppService<T> {
    T findByAppId(Long appId);
    void updateByAppId(Long appId, T t);
}
