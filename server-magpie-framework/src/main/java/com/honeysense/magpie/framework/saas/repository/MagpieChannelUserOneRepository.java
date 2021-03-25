package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelUserOneRepository<T extends MagpieEntity> extends MagpieChannelManyRepository<T> {
    T findByChannelIdAndUserId(Long channelId, Long userId);
}