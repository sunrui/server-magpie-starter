package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelOneRepository<T extends MagpieEntity> extends MagpieChannelRepository<T> {
    T findByChannelId(Long channelId);
}