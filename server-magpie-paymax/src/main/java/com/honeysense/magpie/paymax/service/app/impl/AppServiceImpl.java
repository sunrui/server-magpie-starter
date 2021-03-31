package com.honeysense.magpie.paymax.service.app.impl;

import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.repository.app.AppRepository;
import com.honeysense.magpie.paymax.service.app.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {
    @Autowired
    private AppRepository appRepository;

    @Override
    public App findById(Long id) {
        return appRepository.findById(id).orElse(null);
    }
}
