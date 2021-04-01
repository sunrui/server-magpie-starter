package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieAppUserOneRepository<T extends MagpieEntity> extends MagpieAppManyRepository<T> {
    T findByAppIdAndUserId(Long appId, Long userId);
}