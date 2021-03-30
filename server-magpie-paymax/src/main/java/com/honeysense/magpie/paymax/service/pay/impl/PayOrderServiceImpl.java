package com.honeysense.magpie.paymax.service.pay.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.entity.pay.PayOrder;
import com.honeysense.magpie.paymax.entity.pay.PayOrderStatus;
import com.honeysense.magpie.paymax.repository.account.AccountRepository;
import com.honeysense.magpie.paymax.repository.app.AppRepository;
import com.honeysense.magpie.paymax.repository.pay.PayOrderRepository;
import com.honeysense.magpie.paymax.repository.pay.PayRefundRepository;
import com.honeysense.magpie.paymax.service.pay.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayOrderServiceImpl implements PayOrderService {
    @Autowired
    private AppRepository appRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PayOrderRepository payOrderRepository;
    @Autowired
    private PayRefundRepository payRefundRepository;

    @Override
    public PayOrder insert(Long appId, String appUserId, String gatewayId, String payload, BigDecimal amount, Integer maxAge) {
        if (!MagpieValidator.longId(appId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appId");
        }

        if (!MagpieValidator.enId(appUserId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "appUserId");
        }

        if (!MagpieValidator.enId(gatewayId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "gatewayId");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "amount");
        }

        App app = appRepository.findById(appId).orElse(null);
        if (app == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("appId", app);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        PayOrder payOrder = payOrderRepository.findByGatewayId(gatewayId);
        if (payOrder != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("gatewayId", gatewayId);

            throw new MagpieException(MagpieException.Type.DUPLICATE, map);
        }

        payOrder = new PayOrder();
        payOrder.setAppId(appId);
        payOrder.setAppUserId(appUserId);
        payOrder.setGatewayType(app.getGatewayType());
        payOrder.setGatewayId(gatewayId);
        payOrder.setPayload(payload);
        payOrder.setAmount(amount);
        payOrder.setStatus(PayOrderStatus.WAIT);
        payOrder.setExpiredAt(new Date(new Date().getTime() + maxAge * 1000L));
        payOrderRepository.save(payOrder);

        return payOrder;
    }

    @Override
    public void updateStatus(Long id, PayOrderStatus status) {
        if (!MagpieValidator.longId(id)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "id");
        }

        if (status == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "status");
        }

        PayOrder payOrder = payOrderRepository.findById(id).orElse(null);
        if (payOrder == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        if (payOrder.getStatus() != PayOrderStatus.WAIT) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            throw new MagpieException(MagpieException.Type.BAD_LOGIC, map);
        }

        payOrder.setStatus(status);
        payOrderRepository.save(payOrder);
    }
}
