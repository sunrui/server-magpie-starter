package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieAppOneRepository<T extends MagpieEntity> extends MagpieAppRepository<T> {
    T findByAppId(Long appId);
}