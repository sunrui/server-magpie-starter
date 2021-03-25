package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumSpecBasic;
import com.honeysense.magpie.medium.repository.MediumSpecBasicRepository;
import com.honeysense.magpie.medium.service.MediumSpecBasicService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumSpecBasicServiceImpl extends MagpieServiceImpl<MediumSpecBasic> implements MediumSpecBasicService {
    private final MediumSpecBasicRepository mediumSpecBasicRepository;

    @Autowired
    public MediumSpecBasicServiceImpl(MediumSpecBasicRepository mediumSpecBasicRepository) {
        super(mediumSpecBasicRepository);

        this.mediumSpecBasicRepository = mediumSpecBasicRepository;
    }
}
