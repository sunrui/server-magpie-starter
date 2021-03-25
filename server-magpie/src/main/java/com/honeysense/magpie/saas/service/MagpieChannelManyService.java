package com.honeysense.magpie.saas.service;

import com.honeysense.magpie.entity.MagpieEntity;
import com.honeysense.magpie.entity.MagpiePage;

public interface MagpieChannelManyService<T extends MagpieEntity> extends MagpieChannelService<T> {
    int countAllByChannelId(Long channelId);
    MagpiePage<T> findAllByChannelIdDesc(Long channelId, int page, int size);
    MagpiePage<T> findAllByChannelIdAsc(Long channelId, int page, int size);
}
