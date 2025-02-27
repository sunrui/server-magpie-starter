package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieRepository<T extends MagpieEntity> extends JpaRepository<T, Long> {
}