package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumSpecQuote;
import com.honeysense.magpie.medium.repository.MediumSpecQuoteRepository;
import com.honeysense.magpie.medium.service.MediumSpecQuoteService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumSpecQuoteServiceImpl extends MagpieAppManyServiceImpl<MediumSpecQuote> implements MediumSpecQuoteService {
    private final MediumSpecQuoteRepository mediumSpecQuoteRepository;

    @Autowired
    protected MediumSpecQuoteServiceImpl(MediumSpecQuoteRepository mediumSpecQuoteRepository) {
        super(mediumSpecQuoteRepository);

        this.mediumSpecQuoteRepository = mediumSpecQuoteRepository;
    }
}
