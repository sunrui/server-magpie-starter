package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieAppManyRepository<T extends MagpieEntity> extends MagpieAppRepository<T> {
    int countAllByAppId(Long appId);
    Page<T> findByAppId(Long appId, Pageable pageable);
    Page<T> findByAppIdOrderByCreatedAtDesc(Long appId, Pageable pageable);
}
