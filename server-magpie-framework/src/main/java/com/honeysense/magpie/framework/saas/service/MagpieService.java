package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import com.honeysense.magpie.framework.entity.MagpiePage;

public interface MagpieService<T extends MagpieEntity> {
    T save(T t);
    T findById(Long id);
    MagpiePage<T> findAll(int page, int size);
    void deleteById(Long id);
}
