package com.honeysense.media.service.impl;

import com.honeysense.media.entity.DriverOrder;
import com.honeysense.media.entity.DriverOrderVerify;
import com.honeysense.media.entity.DriverOrderStatus;
import com.honeysense.media.repository.DriverOrderCertifyRepository;
import com.honeysense.media.repository.DriverOrderRepository;
import com.honeysense.media.service.DriverOrderService;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import com.honeysense.magpie.utils.MagpieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class DriverOrderServiceImpl extends MagpieChannelUserManyServiceImpl<DriverOrder> implements DriverOrderService {
    private final DriverOrderRepository driverOrderRepository;
    private final DriverOrderCertifyRepository driverOrderCertifyRepository;

    @Autowired
    protected DriverOrderServiceImpl(DriverOrderRepository driverOrderRepository,
                                     DriverOrderCertifyRepository driverOrderCertifyRepository) {
        super(driverOrderRepository);

        this.driverOrderRepository = driverOrderRepository;
        this.driverOrderCertifyRepository = driverOrderCertifyRepository;
    }

    @Override
    public DriverOrder insertOne(Long channelId, Long userId, Long sponsorPublishId, String sendAddress, String sendUser, String sendPhone) {
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

        DriverOrderStatus[] statuses = new DriverOrderStatus[]{
                DriverOrderStatus.CANCEL, DriverOrderStatus.FINISH
        };

        Page<DriverOrder> servingOrderPage = driverOrderRepository.findAllByChannelIdAndUserIdAndStatusNotIn(channelId, userId, Arrays.asList(statuses), PageRequest.of(0, 1));
        if (servingOrderPage.getSize() > 0) {
            Map<String, Long> map = new HashMap<>();
            map.put("sponsorPublishId", sponsorPublishId);
            map.put("channelId", channelId);
            map.put("userId", userId);

            throw new MagpieException(MagpieException.Type.BAD_LOGIC, map);
        }

        DriverOrder driverOrder = DriverOrder.builder()
                .sponsorPublishId(sponsorPublishId)
                .channelId(channelId)
                .userId(userId)
                .sendAddress(sendAddress)
                .sendUser(sendUser)
                .sendPhone(sendPhone)
                .status(DriverOrderStatus.APPLYING)
                .build();

        driverOrderRepository.save(driverOrder);

        DriverOrderVerify driverOrderVerify = DriverOrderVerify.builder()
                .driverOrder(driverOrder)
                .status(DriverOrderStatus.APPLYING)
                .remark(null)
                .build();

        driverOrderCertifyRepository.save(driverOrderVerify);

        return driverOrder;
    }

    @Override
    public MagpiePage<DriverOrder> findAllByChannelIdAndUserIdAndOrderStatusNotIn(Long channelId, Long userId, DriverOrderStatus[] statuses, int page, int size) {
        if (!MagpieValidator.longId(channelId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "channelId");
        }

        if (!MagpieValidator.longId(userId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "userId");
        }

        if (statuses == null || statuses.length == 0) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "statuses");
        }

        if (page < 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("page", page);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (size <= 0) {
            Map<String, Integer> map = new HashMap<>();
            map.put("size", size);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        Page<DriverOrder> elements = driverOrderRepository.findAllByChannelIdAndUserIdAndStatusNotIn(channelId, userId, Arrays.asList(statuses), PageRequest.of(page, size));
        return new MagpiePage<>(elements);
    }
}
