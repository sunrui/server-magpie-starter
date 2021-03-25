package com.honeysense.magpie.saas.service.impl;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.saas.repository.MagpieRepository;
import com.honeysense.magpie.saas.service.MagpieService;
import com.honeysense.magpie.utils.MagpieValidator;
import com.honeysense.magpie.entity.MagpiePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MagpieServiceImpl<T extends MagpieEntity> implements MagpieService<T> {
    private final MagpieRepository<T> magpieRepository;

    public MagpieServiceImpl(MagpieRepository<T> magpieRepository) {
        this.magpieRepository = magpieRepository;
    }

    @Override
    public T save(T t) {
        MagpieValidator.object(t);

        return magpieRepository.save(t);
    }

    @Override
    public T findById(Long id) {
        if (id == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        Optional<T> r = magpieRepository.findById(id);
        return r.orElse(null);
    }

    @Override
    public MagpiePage<T> findAll(int page, int size) {
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

        Page<T> elements = magpieRepository.findAll(PageRequest.of(page, size));
        return new MagpiePage<>(elements);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        magpieRepository.deleteById(id);
    }
}
