package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieUserManyRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    int countAllByUserId(Long userId);
    Page<T> findAllByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
