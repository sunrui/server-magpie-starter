package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieRepository<T extends MagpieEntity> extends JpaRepository<T, Long> {
}