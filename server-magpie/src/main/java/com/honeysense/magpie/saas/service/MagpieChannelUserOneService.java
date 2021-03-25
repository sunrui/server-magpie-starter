package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;

public interface MagpieChannelUserOneService<T extends MagpieEntity> extends MagpieChannelManyService<T> {
    T findByChannelIdAndUserId(Long channelId, Long userId);
}
