package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import org.springframework.data.domain.PageRequest;

public interface MagpieUserManyService<T extends MagpieEntity> extends MagpieService<T> {
    int countAllByUserId(Long userId);
    MagpiePage<T> findAllByUserId(Long userId, MagpiePageRequest magpiePageRequest);
}
