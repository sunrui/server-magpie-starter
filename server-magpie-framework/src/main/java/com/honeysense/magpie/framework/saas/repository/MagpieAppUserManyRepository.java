package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieAppUserManyRepository<T extends MagpieEntity> extends MagpieAppManyRepository<T> {
    int countAllByAppIdAndUserId(Long appId, Long userId);
    Page<T> findByAppIdAndUserIdOrderByCreatedAtDesc(Long appId, Long userId, Pageable pageable);
}
