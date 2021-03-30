package com.honeysense.magpie.paymax.service.pay.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.entity.pay.PayOrder;
import com.honeysense.magpie.paymax.repository.account.AccountRepository;
import com.honeysense.magpie.paymax.repository.app.AppRepository;
import com.honeysense.magpie.paymax.repository.pay.PayOrderRepository;
import com.honeysense.magpie.paymax.repository.pay.PayRefundRepository;
import com.honeysense.magpie.paymax.service.pay.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public PayOrder insert(Long appId, String appUserId, String gatewayId, String payload, BigDecimal amount) {
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



        return null;
    }
}
