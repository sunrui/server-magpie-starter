package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumFundTradeHistory;
import com.honeysense.magpie.medium.repository.MediumFundTradeHistoryRepository;
import com.honeysense.magpie.medium.service.MediumFundTradeHistoryService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumFundTradeHistoryServiceImpl extends MagpieAppUserManyServiceImpl<MediumFundTradeHistory> implements MediumFundTradeHistoryService {
    private final MediumFundTradeHistoryRepository mediumFundTradeHistoryRepository;

    @Autowired
    protected MediumFundTradeHistoryServiceImpl(MediumFundTradeHistoryRepository mediumFundTradeHistoryRepository) {
        super(mediumFundTradeHistoryRepository);

        this.mediumFundTradeHistoryRepository = mediumFundTradeHistoryRepository;
    }
}
