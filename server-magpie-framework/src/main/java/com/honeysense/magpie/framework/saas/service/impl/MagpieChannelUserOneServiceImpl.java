package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.saas.service.MagpieChannelUserOneService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieChannelUserOneRepository;

import java.util.HashMap;
import java.util.Map;

public class MagpieChannelUserOneServiceImpl<T extends MagpieEntity> extends MagpieChannelManyServiceImpl<T> implements MagpieChannelUserOneService<T> {
    private final MagpieChannelUserOneRepository<T> magpieChannelUserOneRepository;

    protected MagpieChannelUserOneServiceImpl(MagpieChannelUserOneRepository<T> magpieChannelUserOneRepository) {
        super(magpieChannelUserOneRepository);

        this.magpieChannelUserOneRepository = magpieChannelUserOneRepository;
    }

    @Override
    public T findByChannelIdAndUserId(Long channelId, Long userId) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelUserOneRepository.findByChannelIdAndUserId(channelId, userId);
    }
}
