package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.service.MagpieAppUserOneService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieAppUserOneRepository;

import java.util.HashMap;
import java.util.Map;

public class MagpieAppUserOneServiceImpl<T extends MagpieEntity> extends MagpieAppManyServiceImpl<T> implements MagpieAppUserOneService<T> {
    private final MagpieAppUserOneRepository<T> magpieChannelUserOneRepository;

    protected MagpieAppUserOneServiceImpl(MagpieAppUserOneRepository<T> magpieChannelUserOneRepository) {
        super(magpieChannelUserOneRepository);

        this.magpieChannelUserOneRepository = magpieChannelUserOneRepository;
    }

    @Override
    public T findByAppIdAndUserId(Long appId, Long userId) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelUserOneRepository.findByAppIdAndUserId(appId, userId);
    }
}
