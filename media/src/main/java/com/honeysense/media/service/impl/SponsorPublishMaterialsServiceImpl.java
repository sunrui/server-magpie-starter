package com.honeysense.media.service.impl;

import com.honeysense.media.entity.SponsorPublishMaterials;
import com.honeysense.media.repository.SponsorPublishMaterialsRepository;
import com.honeysense.media.service.SponsorPublishMaterialsService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorPublishMaterialsServiceImpl extends MagpieChannelUserManyServiceImpl<SponsorPublishMaterials> implements SponsorPublishMaterialsService {
    private final SponsorPublishMaterialsRepository sponsorPublishMaterialsRepository;

    @Autowired
    protected SponsorPublishMaterialsServiceImpl(SponsorPublishMaterialsRepository sponsorPublishMaterialsRepository) {
        super(sponsorPublishMaterialsRepository);

        this.sponsorPublishMaterialsRepository = sponsorPublishMaterialsRepository;
    }
}
