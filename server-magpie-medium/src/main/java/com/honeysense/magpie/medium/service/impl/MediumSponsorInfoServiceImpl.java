package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumSponsorInfo;
import com.honeysense.magpie.medium.repository.MediumSponsorInfoRepository;
import com.honeysense.magpie.medium.service.MediumSponsorInfoService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieChannelUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumSponsorInfoServiceImpl extends MagpieChannelUserOneServiceImpl<MediumSponsorInfo> implements MediumSponsorInfoService {
    private final MediumSponsorInfoRepository MediumSponsorInfoRepository;

    @Autowired
    protected MediumSponsorInfoServiceImpl(MediumSponsorInfoRepository MediumSponsorInfoRepository) {
        super(MediumSponsorInfoRepository);

        this.MediumSponsorInfoRepository = MediumSponsorInfoRepository;
    }
}
