package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.repository.MagpieAppOneRepository;
import com.honeysense.magpie.framework.saas.service.MagpieAppOneService;
import com.honeysense.magpie.framework.utils.MagpieValidator;

import java.util.HashMap;
import java.util.Map;

public class MagpieAppOneServiceImpl<T extends MagpieEntity> extends MagpieAppServiceImpl<T> implements MagpieAppOneService<T> {
    private final MagpieAppOneRepository<T> magpieAppOneRepository;

    protected MagpieAppOneServiceImpl(MagpieAppOneRepository<T> magpieAppOneRepository) {
        super(magpieAppOneRepository);

        this.magpieAppOneRepository = magpieAppOneRepository;
    }

    @Override
    public T findByAppId(Long appId) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieAppOneRepository.findByAppId(appId);
    }

    @Override
    public void updateByAppId(Long appId, T t) {
        if (!MagpieValidator.longId(appId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (t == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "t");
        }

        T one = magpieAppOneRepository.findByAppId(appId);
        if (one != null) {
            t.setId(one.getId());
        }

        magpieAppOneRepository.save(t);
    }
}
