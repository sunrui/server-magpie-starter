package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieAppManyService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieAppManyRepository;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class MagpieAppManyServiceImpl<T extends MagpieEntity> extends MagpieAppServiceImpl<T> implements MagpieAppManyService<T> {
    private final MagpieAppManyRepository<T> magpieAppManyRepository;

    protected MagpieAppManyServiceImpl(MagpieAppManyRepository<T> magpieAppManyRepository) {
        super(magpieAppManyRepository);

        this.magpieAppManyRepository = magpieAppManyRepository;
    }

    @Override
    public int countAllByAppId(Long appId) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieAppManyRepository.countAllByAppId(appId);
    }

    @Override
    public MagpiePage<T> findAllByAppIdDesc(Long appId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieAppManyRepository.findByAppIdOrderByCreatedAtDesc(appId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }

    @Override
    public MagpiePage<T> findAllByAppIdAsc(Long appId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieAppManyRepository.findByAppId(appId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
