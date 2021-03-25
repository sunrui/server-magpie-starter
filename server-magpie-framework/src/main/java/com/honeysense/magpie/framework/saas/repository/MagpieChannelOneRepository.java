package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelOneRepository<T extends MagpieEntity> extends MagpieChannelRepository<T> {
    T findByChannelId(Long channelId);
}