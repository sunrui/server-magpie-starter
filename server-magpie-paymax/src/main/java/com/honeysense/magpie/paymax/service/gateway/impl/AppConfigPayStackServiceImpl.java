package com.honeysense.magpie.paymax.service.gateway.impl;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.paymax.entity.app.AppConfigPayStack;
import com.honeysense.magpie.paymax.repository.app.AppConfigPayStackRepository;
import com.honeysense.magpie.paymax.service.gateway.AppConfigPayStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppConfigPayStackServiceImpl extends MagpieServiceImpl<AppConfigPayStack> implements AppConfigPayStackService {
    @Autowired
    private AppConfigPayStackRepository appConfigPayStackRepository;

    protected AppConfigPayStackServiceImpl(AppConfigPayStackRepository appConfigPayStackRepository) {
        super(appConfigPayStackRepository);
    }

    @Override
    public AppConfigPayStack findByName(String name) {
        if (!MagpieValidator.string(name)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "name");
        }

        return appConfigPayStackRepository.findByName(name);
    }
}
