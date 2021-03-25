package com.honeysense.magpie.saas.service.impl;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.saas.repository.MagpieChannelOneRepository;
import com.honeysense.magpie.saas.service.MagpieChannelOneService;
import com.honeysense.magpie.utils.MagpieValidator;

import java.util.HashMap;
import java.util.Map;

public class MagpieChannelOneServiceImpl<T extends MagpieEntity> extends MagpieChannelServiceImpl<T> implements MagpieChannelOneService<T> {
    private final MagpieChannelOneRepository<T> magpieChannelOneRepository;

    protected MagpieChannelOneServiceImpl(MagpieChannelOneRepository<T> magpieChannelOneRepository) {
        super(magpieChannelOneRepository);

        this.magpieChannelOneRepository = magpieChannelOneRepository;
    }

    @Override
    public T findByChannelId(Long channelId) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelOneRepository.findByChannelId(channelId);
    }

    @Override
    public void updateByChannelId(Long channelId, T t) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (t == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "t");
        }

        T one = magpieChannelOneRepository.findByChannelId(channelId);
        if (one != null) {
            t.setId(one.getId());
        }

        magpieChannelOneRepository.save(t);
    }
}
