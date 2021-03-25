package com.honeysense.magpie.saas.service.impl;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.saas.repository.MagpieUserOneRepository;
import com.honeysense.magpie.saas.service.MagpieUserOneService;
import com.honeysense.magpie.utils.MagpieValidator;

import java.util.HashMap;
import java.util.Map;

public class MagpieUserOneServiceImpl<T extends MagpieEntity> extends MagpieServiceImpl<T> implements MagpieUserOneService<T> {
    private final MagpieUserOneRepository<T> hazeUserOneRepository;

    protected MagpieUserOneServiceImpl(MagpieUserOneRepository<T> hazeUserOneRepository) {
        super(hazeUserOneRepository);

        this.hazeUserOneRepository = hazeUserOneRepository;
    }

    @Override
    public T findByUserId(Long userId) {
        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return hazeUserOneRepository.findByUserId(userId);
    }
}
