package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieAppUserManyService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieAppUserManyRepository;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class MagpieAppUserManyServiceImpl<T extends MagpieEntity> extends MagpieAppManyServiceImpl<T> implements MagpieAppUserManyService<T> {
    private final MagpieAppUserManyRepository<T> magpieChannelUserManyRepository;

    protected MagpieAppUserManyServiceImpl(MagpieAppUserManyRepository<T> magpieChannelUserManyRepository) {
        super(magpieChannelUserManyRepository);

        this.magpieChannelUserManyRepository = magpieChannelUserManyRepository;
    }

    @Override
    public int countAllByAppIdAndUserId(Long appId, Long userId) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieChannelUserManyRepository.countAllByAppIdAndUserId(appId, userId);
    }

    @Override
    public MagpiePage<T> findAllByAppIdAndUserId(Long appId, Long userId, MagpiePageRequest magpiePageRequest) {
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

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieChannelUserManyRepository.findByAppIdAndUserIdOrderByCreatedAtDesc(appId, userId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
