package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;

public interface MagpieService<T extends MagpieEntity> {
    T save(T t);
    T findById(Long id);
    MagpiePage<T> findAll(MagpiePageRequest magpiePageRequest);
    void deleteById(Long id);
}
