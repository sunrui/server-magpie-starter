package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumSponsorPublish;
import com.honeysense.magpie.medium.repository.MediumSponsorPublishRepository;
import com.honeysense.magpie.medium.service.MediumSponsorPublishService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumSponsorPublishServiceImpl extends MagpieAppUserManyServiceImpl<MediumSponsorPublish> implements MediumSponsorPublishService {
    private final MediumSponsorPublishRepository mediumSponsorPublishRepository;

    @Autowired
    protected MediumSponsorPublishServiceImpl(MediumSponsorPublishRepository mediumSponsorPublishRepository) {
        super(mediumSponsorPublishRepository);

        this.mediumSponsorPublishRepository = mediumSponsorPublishRepository;
    }
}
