package com.honeysense.magpie.paymax.service.gateway.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import com.honeysense.magpie.paymax.entity.gateway.GatewayPayStack;
import com.honeysense.magpie.paymax.repository.gateway.GatewayPayStackRepository;
import com.honeysense.magpie.paymax.service.gateway.GatewayPayStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayPayStackServiceImpl extends MagpieAppUserManyServiceImpl<GatewayPayStack> implements GatewayPayStackService {
    @Autowired
    private GatewayPayStackRepository gatewayPayStackRepository;

    protected GatewayPayStackServiceImpl(GatewayPayStackRepository gatewayPayStackRepository) {
        super(gatewayPayStackRepository);
    }
}
