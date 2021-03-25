package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.entity.MediumDriverOrderStatus;
import com.honeysense.magpie.medium.entity.MediumDriverOrderVerify;
import com.honeysense.magpie.medium.repository.MediumDriverOrderCertifyRepository;
import com.honeysense.magpie.medium.repository.MediumDriverOrderRepository;
import com.honeysense.magpie.medium.service.MediumDriverOrderService;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.saas.service.impl.MagpieChannelUserManyServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class MediumDriverOrderServiceImpl extends MagpieChannelUserManyServiceImpl<MediumDriverOrder> implements MediumDriverOrderService {
    private final MediumDriverOrderRepository mediumDriverOrderRepository;
    private final MediumDriverOrderCertifyRepository mediumDriverOrderCertifyRepository;

    @Autowired
    protected MediumDriverOrderServiceImpl(MediumDriverOrderRepository mediumDriverOrderRepository,
                                           MediumDriverOrderCertifyRepository mediumDriverOrderCertifyRepository) {
        super(mediumDriverOrderRepository);

        this.mediumDriverOrderRepository = mediumDriverOrderRepository;
        this.mediumDriverOrderCertifyRepository = mediumDriverOrderCertifyRepository;
    }

    @Override
    public MediumDriverOrder insertOne(Long channelId, Long userId, Long sponsorPublishId, String sendAddress, String sendUser, String sendPhone) {
        if (!MagpieValidator.longId(channelId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "channelId");
        }

        if (!MagpieValidator.longId(userId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "userId");
        }

        if (!MagpieValidator.longId(sponsorPublishId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "sponsorPublishId");
        }

        if (!MagpieValidator.string(sendAddress)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "sendAddress");
        }

        if (!MagpieValidator.string(sendUser)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "sendUser");
        }

        if (!MagpieValidator.string(sendPhone)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "sendPhone");
        }

        MediumDriverOrderStatus[] statuses = new MediumDriverOrderStatus[]{
                MediumDriverOrderStatus.CANCEL, MediumDriverOrderStatus.FINISH
        };

        Page<MediumDriverOrder> servingOrderPage = mediumDriverOrderRepository.findAllByChannelIdAndUserIdAndStatusNotIn(channelId, userId, Arrays.asList(statuses), PageRequest.of(0, 1));
        if (servingOrderPage.getSize() > 0) {
            Map<String, Long> map = new HashMap<>();
            map.put("sponsorPublishId", sponsorPublishId);
            map.put("channelId", channelId);
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.BAD_LOGIC, map);
        }

        MediumDriverOrder mediumDriverOrder = MediumDriverOrder.builder()
                .sponsorPublishId(sponsorPublishId)
                .channelId(channelId)
                .userId(userId)
                .sendAddress(sendAddress)
                .sendUser(sendUser)
                .sendPhone(sendPhone)
                .status(MediumDriverOrderStatus.APPLYING)
                .build();

        mediumDriverOrderRepository.save(mediumDriverOrder);

        MediumDriverOrderVerify mediumDriverOrderVerify = MediumDriverOrderVerify.builder()
                .mediumDriverOrder(mediumDriverOrder)
                .status(MediumDriverOrderStatus.APPLYING)
                .remark(null)
                .build();

        mediumDriverOrderCertifyRepository.save(mediumDriverOrderVerify);

        return mediumDriverOrder;
    }

    @Override
    public MagpiePage<MediumDriverOrder> findAllByChannelIdAndUserIdAndOrderStatusNotIn(Long channelId, Long userId, MediumDriverOrderStatus[] statuses, MagpiePageRequest magpiePageRequest) {
        if (!MagpieValidator.longId(channelId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "channelId");
        }

        if (!MagpieValidator.longId(userId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "userId");
        }

        if (statuses == null || statuses.length == 0) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "statuses");
        }

        MagpieValidator.object(magpiePageRequest);

        Page<MediumDriverOrder> elements = mediumDriverOrderRepository.findAllByChannelIdAndUserIdAndStatusNotIn(channelId, userId, Arrays.asList(statuses), magpiePageRequest.of());
        return new MagpiePage<>(elements);
    }
}
