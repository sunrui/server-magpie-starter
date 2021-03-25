package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.entity.MagpieEntity;

public interface MagpieChannelOneService<T extends MagpieEntity> extends MagpieChannelService<T> {
    T findByChannelId(Long channelId);
    void updateByChannelId(Long channelId, T t);
}
