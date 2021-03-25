package com.honeysense.media.service;

import com.honeysense.media.entity.DriverOrder;
import com.honeysense.media.entity.DriverOrderStatus;
import com.honeysense.magpie.saas.service.MagpieChannelUserManyService;
import com.honeysense.magpie.entity.MagpiePage;

public interface DriverOrderService extends MagpieChannelUserManyService<DriverOrder> {
    DriverOrder insertOne(Long channelId, Long userId, Long sponsorPublishId, String sendAddress, String sendUser, String sendPhone);
    MagpiePage<DriverOrder> findAllByChannelIdAndUserIdAndOrderStatusNotIn(Long channelId, Long userId, DriverOrderStatus[] statuses, int page, int size);
}
