package com.honeysense.magpie.saas.service.impl;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.saas.repository.MagpieChannelUserOneRepository;
import com.honeysense.magpie.saas.service.MagpieChannelUserOneService;
import com.honeysense.magpie.utils.MagpieValidator;

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
