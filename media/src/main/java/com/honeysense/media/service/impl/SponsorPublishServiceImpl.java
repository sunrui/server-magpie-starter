package com.honeysense.media.service.impl;

import com.honeysense.media.entity.SponsorPublish;
import com.honeysense.media.repository.SponsorPublishRepository;
import com.honeysense.media.service.SponsorPublishService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorPublishServiceImpl extends MagpieChannelUserManyServiceImpl<SponsorPublish> implements SponsorPublishService {
    private final SponsorPublishRepository sponsorPublishRepository;

    @Autowired
    protected SponsorPublishServiceImpl(SponsorPublishRepository sponsorPublishRepository) {
        super(sponsorPublishRepository);

        this.sponsorPublishRepository = sponsorPublishRepository;
    }
}
