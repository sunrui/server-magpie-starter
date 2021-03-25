package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.service.MagpieUserManyService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.saas.repository.MagpieUserManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;

public class MagpieUserManyServiceImpl<T extends MagpieEntity> extends MagpieServiceImpl<T> implements MagpieUserManyService<T> {
    private final MagpieUserManyRepository<T> magpieUserManyRepository;

    protected MagpieUserManyServiceImpl(MagpieUserManyRepository<T> magpieUserManyRepository) {
        super(magpieUserManyRepository);

        this.magpieUserManyRepository = magpieUserManyRepository;
    }

    @Override
    public int countAllByUserId(Long userId) {
        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("Id", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        return magpieUserManyRepository.countAllByUserId(userId);
    }

    @Override
    public MagpiePage<T> findAllByUserId(Long userId, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieUserManyRepository.findAllByUserIdOrderByCreatedAtDesc(userId, magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
