package com.honeysense.magpie.framework.saas.service;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;

public interface MagpieChannelManyService<T extends MagpieEntity> extends MagpieChannelService<T> {
    int countAllByChannelId(Long channelId);
    MagpiePage<T> findAllByChannelIdDesc(Long channelId, MagpiePageRequest magpiePageRequest);
    MagpiePage<T> findAllByChannelIdAsc(Long channelId, MagpiePageRequest magpiePageRequest);
}
