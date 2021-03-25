package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;

public interface MagpieChannelUserManyService<T extends MagpieEntity> extends MagpieChannelManyService<T> {
    int countAllByChannelIdAndUserId(Long channelId, Long userId);
    MagpiePage<T> findAllByChannelIdAndUserId(Long channelId, Long userId, MagpiePageRequest magpiePageRequest);
}
