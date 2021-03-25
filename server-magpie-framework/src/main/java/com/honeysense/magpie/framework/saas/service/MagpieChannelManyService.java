package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import com.honeysense.magpie.framework.entity.MagpiePage;

public interface MagpieChannelManyService<T extends MagpieEntity> extends MagpieChannelService<T> {
    int countAllByChannelId(Long channelId);
    MagpiePage<T> findAllByChannelIdDesc(Long channelId, int page, int size);
    MagpiePage<T> findAllByChannelIdAsc(Long channelId, int page, int size);
}
