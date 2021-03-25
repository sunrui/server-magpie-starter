package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieUserManyRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    int countAllByUserId(Long userId);
    Page<T> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
