package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;

public interface MagpieChannelService<T extends MagpieEntity> extends MagpieService<T> {
    T findByChannelIdAndId(Long channelId, Long id);
    void updateByChannelIdAndId(Long channelId, Long id, T t);
    void deleteByChannelIdAndId(Long channelId, Long id);
}
