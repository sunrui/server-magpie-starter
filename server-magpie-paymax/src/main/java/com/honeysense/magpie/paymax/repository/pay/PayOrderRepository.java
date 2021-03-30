package com.honeysense.magpie.paymax.repository.pay;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.paymax.entity.pay.PayOrder;

public interface PayOrderRepository extends MagpieRepository<PayOrder> {
    PayOrder findByGatewayId(String gatewayId);
}
