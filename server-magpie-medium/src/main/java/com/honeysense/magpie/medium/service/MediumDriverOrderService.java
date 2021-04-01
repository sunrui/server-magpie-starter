package com.honeysense.magpie.medium.service;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.entity.MediumDriverOrderStatus;
import com.honeysense.magpie.framework.saas.service.MagpieAppUserManyService;
import com.honeysense.magpie.framework.object.MagpiePage;

public interface MediumDriverOrderService extends MagpieAppUserManyService<MediumDriverOrder> {
    MediumDriverOrder insertOne(Long appId, Long userId, Long sponsorPublishId, String sendAddress, String sendUser, String sendPhone);
    MagpiePage<MediumDriverOrder> findAllByAppIdAndUserIdAndOrderStatusNotIn(Long appId, Long userId, MediumDriverOrderStatus[] statuses, MagpiePageRequest magpiePageRequest);
}
