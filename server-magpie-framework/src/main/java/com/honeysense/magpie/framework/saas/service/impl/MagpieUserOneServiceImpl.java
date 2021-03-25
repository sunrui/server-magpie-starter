package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.service.MagpieUserOneService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieUserOneRepository;

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
