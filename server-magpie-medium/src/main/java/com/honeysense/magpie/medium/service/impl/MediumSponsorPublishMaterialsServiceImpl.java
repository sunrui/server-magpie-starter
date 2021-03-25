package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumSponsorPublishMaterials;
import com.honeysense.magpie.medium.repository.MediumSponsorPublishMaterialsRepository;
import com.honeysense.magpie.medium.service.MediumSponsorPublishMaterialsService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumSponsorPublishMaterialsServiceImpl extends MagpieChannelUserManyServiceImpl<MediumSponsorPublishMaterials> implements MediumSponsorPublishMaterialsService {
    private final MediumSponsorPublishMaterialsRepository mediumSponsorPublishMaterialsRepository;

    @Autowired
    protected MediumSponsorPublishMaterialsServiceImpl(MediumSponsorPublishMaterialsRepository mediumSponsorPublishMaterialsRepository) {
        super(mediumSponsorPublishMaterialsRepository);

        this.mediumSponsorPublishMaterialsRepository = mediumSponsorPublishMaterialsRepository;
    }
}
