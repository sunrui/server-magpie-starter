package com.honeysense.magpie.paymax.service.pay.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import com.honeysense.magpie.paymax.entity.pay.PayRefund;
import com.honeysense.magpie.paymax.repository.pay.PayRefundRepository;
import com.honeysense.magpie.paymax.service.pay.PayRefundBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayRefundBillServiceImpl extends MagpieAppUserManyServiceImpl<PayRefund> implements PayRefundBillService {
    @Autowired
    private PayRefundRepository payRefundRepository;

    protected PayRefundBillServiceImpl(PayRefundRepository payRefundRepository) {
        super(payRefundRepository);
    }
}
