package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieChannelUserManyService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieChannelUserManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;

public class MagpieChannelUserManyServiceImpl<T extends MagpieEntity> extends MagpieChannelManyServiceImpl<T> implements MagpieChannelUserManyService<T> {
    private final MagpieChannelUserManyRepository<T> magpieChannelUserManyRepository;

    protected MagpieChannelUserManyServiceImpl(MagpieChannelUserManyRepository<T> magpieChannelUserManyRepository) {
        super(magpieChannelUserManyRepository);

        this.magpieChannelUserManyRepository = magpieChannelUserManyRepository;
    }

    @Override
    public int countAllByChannelIdAndUserId(Long channelId, Long userId) {
        if (!MagpieValidator.longId(channelId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("channelId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", channelId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelUserManyRepository.countAllByChannelIdAndUserId(channelId, userId);
    }

    @Override
    public MagpiePage<T> findAllByChannelIdAndUserId(Long channelId, Long userId, MagpiePageRequest magpiePageRequest) {
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

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieChannelUserManyRepository.findByChannelIdAndUserIdOrderByCreatedAtDesc(channelId, userId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
