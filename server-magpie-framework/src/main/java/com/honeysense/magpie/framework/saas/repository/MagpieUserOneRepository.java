package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieUserOneRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    T findByUserId(Long userId);
}
