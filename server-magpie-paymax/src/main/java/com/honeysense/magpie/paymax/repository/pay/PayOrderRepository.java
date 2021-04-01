package com.honeysense.magpie.paymax.repository.pay;

import com.honeysense.magpie.framework.saas.repository.MagpieAppUserManyRepository;
import com.honeysense.magpie.paymax.entity.pay.PayOrder;

public interface PayOrderRepository extends MagpieAppUserManyRepository<PayOrder> {
    PayOrder findByGatewayId(String gatewayId);
}
