package com.honeysense.magpie.framework.saas.repository;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelManyRepository<T extends MagpieEntity> extends MagpieChannelRepository<T> {
    int countAllByChannelId(Long channelId);
    Page<T> findByChannelId(Long channelId, Pageable pageable);
    Page<T> findByChannelIdOrderByCreatedAtDesc(Long channelId, Pageable pageable);
}
