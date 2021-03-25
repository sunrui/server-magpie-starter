package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelUserOneRepository<T extends MagpieEntity> extends MagpieChannelManyRepository<T> {
    T findByChannelIdAndUserId(Long channelId, Long userId);
}