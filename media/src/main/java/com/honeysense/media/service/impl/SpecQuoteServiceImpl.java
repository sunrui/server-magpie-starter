package com.honeysense.media.service.impl;

import com.honeysense.media.entity.SpecQuote;
import com.honeysense.media.repository.SpecQuoteRepository;
import com.honeysense.media.service.SpecQuoteService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecQuoteServiceImpl extends MagpieChannelManyServiceImpl<SpecQuote> implements SpecQuoteService {
    private final SpecQuoteRepository specQuoteRepository;

    @Autowired
    protected SpecQuoteServiceImpl(SpecQuoteRepository specQuoteRepository) {
        super(specQuoteRepository);

        this.specQuoteRepository = specQuoteRepository;
    }
}
