package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;

public interface MagpieAppManyService<T extends MagpieEntity> extends MagpieAppService<T> {
    int countAllByAppId(Long appId);
    MagpiePage<T> findAllByAppIdDesc(Long appId, MagpiePageRequest magpiePageRequest);
    MagpiePage<T> findAllByAppIdAsc(Long appId, MagpiePageRequest magpiePageRequest);
}
