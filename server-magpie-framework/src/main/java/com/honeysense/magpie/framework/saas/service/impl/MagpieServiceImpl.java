package com.honeysense.magpie.framework.saas.service.impl;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.framework.saas.service.MagpieService;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class MagpieServiceImpl<T extends MagpieEntity> implements MagpieService<T> {
    private final MagpieRepository<T> magpieRepository;

    public MagpieServiceImpl(MagpieRepository<T> magpieRepository) {
        this.magpieRepository = magpieRepository;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor= RuntimeException.class)
    public T save(T t) {
        MagpieValidator.object(t);

        T t1 = magpieRepository.save(t);
        if (t1 != null) {
            throw  new RuntimeException("MagpieException.Type.INVALID_PARAMETER");
        }

        return t1;
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
    public MagpiePage<T> findAll(MagpiePageRequest magpiePageRequest) {
        MagpieValidator.object(magpiePageRequest);

        Page<T> elements = magpieRepository.findAll(magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (id == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        magpieRepository.deleteById(id);
    }
}
