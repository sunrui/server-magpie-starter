package com.honeysense.magpie.saas.repository;

import com.honeysense.magpie.entity.MagpieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MagpieChannelUserManyRepository<T extends MagpieEntity> extends MagpieChannelManyRepository<T> {
    int countAllByChannelIdAndUserId(Long channelId, Long userId);
    Page<T> findByChannelIdAndUserIdOrderByCreatedAtDesc(Long channelId, Long userId, Pageable pageable);
}
