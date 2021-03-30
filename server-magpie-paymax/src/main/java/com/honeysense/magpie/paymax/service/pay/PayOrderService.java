package com.honeysense.magpie.paymax.service.pay;

import com.honeysense.magpie.paymax.entity.pay.PayOrder;
import com.honeysense.magpie.paymax.entity.pay.PayOrderStatus;

import java.math.BigDecimal;

public interface PayOrderService {
    PayOrder insert(Long appId, String appUserId, String gatewayId, String payload, BigDecimal amount, Integer maxAge);
    void updateStatus(Long id, PayOrderStatus status);
}
