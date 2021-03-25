package com.honeysense.magpie.medium.service;

import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.entity.MediumDriverOrderStatus;
import com.honeysense.magpie.framework.saas.service.MagpieChannelUserManyService;
import com.honeysense.magpie.framework.entity.MagpiePage;

public interface MediumDriverOrderService extends MagpieChannelUserManyService<MediumDriverOrder> {
    MediumDriverOrder insertOne(Long channelId, Long userId, Long sponsorPublishId, String sendAddress, String sendUser, String sendPhone);
    MagpiePage<MediumDriverOrder> findAllByChannelIdAndUserIdAndOrderStatusNotIn(Long channelId, Long userId, MediumDriverOrderStatus[] statuses, int page, int size);
}
