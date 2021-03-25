package com.honeysense.media.service.impl;

import com.honeysense.media.entity.SpecBasic;
import com.honeysense.media.repository.SpecBasicRepository;
import com.honeysense.media.service.SpecBasicService;
import com.honeysense.magpie.saas.service.impl.MagpieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecBasicServiceImpl extends MagpieServiceImpl<SpecBasic> implements SpecBasicService {
    private final SpecBasicRepository specBasicRepository;

    @Autowired
    public SpecBasicServiceImpl(SpecBasicRepository specBasicRepository) {
        super(specBasicRepository);

        this.specBasicRepository = specBasicRepository;
    }
}
