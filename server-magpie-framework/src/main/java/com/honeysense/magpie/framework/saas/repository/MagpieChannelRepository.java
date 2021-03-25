package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.object.MagpieEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelRepository<T extends MagpieEntity> extends MagpieRepository<T> {
    T findByChannelIdAndId(Long channelId, Long id);
    void deleteByChannelIdAndId(Long channelId, Long id);
}
