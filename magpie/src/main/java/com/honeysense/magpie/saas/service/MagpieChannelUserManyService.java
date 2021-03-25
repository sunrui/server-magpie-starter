package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpiePage;

public interface MagpieChannelUserManyService<T extends MagpieEntity> extends MagpieChannelManyService<T> {
    int countAllByChannelIdAndUserId(Long channelId, Long userId);
    MagpiePage<T> findAllByChannelIdAndUserId(Long channelId, Long userId, int page, int size);
}
