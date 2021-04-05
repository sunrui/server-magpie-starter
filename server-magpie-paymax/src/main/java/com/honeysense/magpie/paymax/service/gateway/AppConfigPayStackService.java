package com.honeysense.magpie.paymax.service.gateway;

import com.honeysense.magpie.framework.saas.service.MagpieService;
import com.honeysense.magpie.paymax.entity.app.AppConfigPayStack;

public interface AppConfigPayStackService extends MagpieService<AppConfigPayStack> {
    AppConfigPayStack findByName(String name);
}
