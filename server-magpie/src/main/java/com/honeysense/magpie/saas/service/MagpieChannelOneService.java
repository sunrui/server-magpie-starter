package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;

public interface MagpieChannelOneService<T extends MagpieEntity> extends MagpieChannelService<T> {
    T findByChannelId(Long channelId);
    void updateByChannelId(Long channelId, T t);
}
