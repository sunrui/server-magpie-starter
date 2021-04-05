package com.honeysense.magpie.paymax.repository.app;

import com.honeysense.magpie.framework.saas.repository.MagpieRepository;
import com.honeysense.magpie.paymax.entity.app.AppConfigPayStack;

public interface AppConfigPayStackRepository extends MagpieRepository<AppConfigPayStack> {
    AppConfigPayStack findByName(String name);
}
