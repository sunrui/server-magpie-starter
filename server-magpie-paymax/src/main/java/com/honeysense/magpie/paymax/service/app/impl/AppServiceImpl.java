package com.honeysense.magpie.paymax.service.app.impl;

import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.repository.app.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl extends MagpieServiceImpl<App> {
    @Autowired
    private AppRepository appRepository;

    public AppServiceImpl(AppRepository appRepository) {
        super(appRepository);
    }
}
