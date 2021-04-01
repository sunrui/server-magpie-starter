package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.repository.MagpieAppRepository;
import com.honeysense.magpie.framework.saas.service.MagpieAppService;
import com.honeysense.magpie.framework.utils.MagpieValidator;

import java.util.HashMap;
import java.util.Map;

public class MagpieAppServiceImpl<T extends MagpieEntity> extends MagpieServiceImpl<T> implements MagpieAppService<T> {
    private final MagpieAppRepository<T> magpieAppRepository;

    MagpieAppServiceImpl(MagpieAppRepository<T> magpieAppRepository) {
        super(magpieAppRepository);

        this.magpieAppRepository = magpieAppRepository;
    }

    @Override
    public T findByAppIdAndId(Long appId, Long id) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieAppRepository.findByAppIdAndId(appId, id);
    }

    @Override
    public void updateByAppIdAndId(Long appId, Long id, T t) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        T one = magpieAppRepository.findByAppIdAndId(appId, id);
        if (one != null) {
            t.setId(one.getId());
        }

        magpieAppRepository.save(t);
    }

    @Override
    public void deleteByAppIdAndId(Long appId, Long id) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (!MagpieValidator.longId(id)) {
            Map<String, Long> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        magpieAppRepository.deleteByAppIdAndId(appId, id);
    }
}
