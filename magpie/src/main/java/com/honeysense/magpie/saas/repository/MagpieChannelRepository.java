package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    T findByChannelIdAndId(Long channelId, Long id);
    void deleteByChannelIdAndId(Long channelId, Long id);
}
