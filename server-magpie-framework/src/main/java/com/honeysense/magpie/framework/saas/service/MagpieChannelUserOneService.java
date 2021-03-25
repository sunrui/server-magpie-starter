package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;

public interface MagpieChannelUserOneService<T extends MagpieEntity> extends MagpieChannelManyService<T> {
    T findByChannelIdAndUserId(Long channelId, Long userId);
}
