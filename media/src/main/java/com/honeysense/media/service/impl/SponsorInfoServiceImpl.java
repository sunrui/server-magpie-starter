package com.honeysense.media.service.impl;

import com.honeysense.media.entity.SponsorInfo;
import com.honeysense.media.repository.SponsorInfoRepository;
import com.honeysense.media.service.SponsorInfoService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserOneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SponsorInfoServiceImpl extends MagpieChannelUserOneServiceImpl<SponsorInfo> implements SponsorInfoService {
    private final com.honeysense.media.repository.SponsorInfoRepository SponsorInfoRepository;

    @Autowired
    protected SponsorInfoServiceImpl(SponsorInfoRepository SponsorInfoRepository) {
        super(SponsorInfoRepository);

        this.SponsorInfoRepository = SponsorInfoRepository;
    }
}
