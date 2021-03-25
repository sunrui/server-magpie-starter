package com.honeysense.magpie.saas.service.impl;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.saas.repository.MagpieUserManyRepository;
import com.honeysense.magpie.saas.service.MagpieUserManyService;
import com.honeysense.magpie.utils.MagpieValidator;
import com.honeysense.magpie.entity.MagpiePage;
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
    public MagpiePage<T> findAllByUserId(Long userId, int page, int size) {
        if (!MagpieValidator.longId(userId)) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (page < 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (size <= 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("size", size);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        Page<T> elements = magpieUserManyRepository.findAllByUserIdOrderByCreatedAtDesc(userId, PageRequest.of(page, size));
        return new MagpiePage<>(elements);
    }
}
