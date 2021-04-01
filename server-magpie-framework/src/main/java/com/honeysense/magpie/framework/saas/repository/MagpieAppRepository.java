package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieAppRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    T findByAppIdAndId(Long appId, Long id);
    void deleteByAppIdAndId(Long appId, Long id);
}
