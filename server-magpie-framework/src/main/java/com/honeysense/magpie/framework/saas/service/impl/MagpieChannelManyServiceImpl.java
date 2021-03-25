package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieChannelManyService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieChannelManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;

public class MagpieChannelManyServiceImpl<T extends MagpieEntity> extends MagpieChannelServiceImpl<T> implements MagpieChannelManyService<T> {
    private final MagpieChannelManyRepository<T> magpieChannelManyRepository;

    protected MagpieChannelManyServiceImpl(MagpieChannelManyRepository<T> magpieChannelManyRepository) {
        super(magpieChannelManyRepository);

        this.magpieChannelManyRepository = magpieChannelManyRepository;
    }

    @Override
    public int countAllByChannelId(Long channelId) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelManyRepository.countAllByChannelId(channelId);
    }

    @Override
    public MagpiePage<T> findAllByChannelIdDesc(Long channelId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieChannelManyRepository.findByChannelIdOrderByCreatedAtDesc(channelId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }

    @Override
    public MagpiePage<T> findAllByChannelIdAsc(Long channelId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieChannelManyRepository.findByChannelId(channelId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
