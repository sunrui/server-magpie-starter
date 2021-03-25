package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.saas.repository.MagpieChannelRepository;
import com.honeysense.magpie.framework.saas.service.MagpieChannelService;
import com.honeysense.magpie.framework.utils.MagpieValidator;

import java.util.HashMap;
import java.util.Map;

public class MagpieChannelServiceImpl<T extends MagpieEntity> extends MagpieServiceImpl<T> implements MagpieChannelService<T> {
    private final MagpieChannelRepository<T> magpieChannelRepository;

    MagpieChannelServiceImpl(MagpieChannelRepository<T> magpieChannelRepository) {
        super(magpieChannelRepository);

        this.magpieChannelRepository = magpieChannelRepository;
    }

    @Override
    public T findByChannelIdAndId(Long channelId, Long id) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelRepository.findByChannelIdAndId(channelId, id);
    }

    @Override
    public void updateByChannelIdAndId(Long channelId, Long id, T t) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        T one = magpieChannelRepository.findByChannelIdAndId(channelId, id);
        if (one != null) {
            t.setId(one.getId());
        }

        magpieChannelRepository.save(t);
    }

    @Override
    public void deleteByChannelIdAndId(Long channelId, Long id) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        magpieChannelRepository.deleteByChannelIdAndId(channelId, id);
    }
}
