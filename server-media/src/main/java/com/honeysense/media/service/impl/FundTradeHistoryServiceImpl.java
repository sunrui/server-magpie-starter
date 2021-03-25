package com.honeysense.media.service.impl;

import com.honeysense.media.entity.FundTradeHistory;
import com.honeysense.media.repository.FundTradeHistoryRepository;
import com.honeysense.media.service.FundTradeHistoryService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundTradeHistoryServiceImpl extends MagpieChannelUserManyServiceImpl<FundTradeHistory> implements FundTradeHistoryService {
    private final FundTradeHistoryRepository fundTradeHistoryRepository;

    @Autowired
    protected FundTradeHistoryServiceImpl(FundTradeHistoryRepository fundTradeHistoryRepository) {
        super(fundTradeHistoryRepository);

        this.fundTradeHistoryRepository = fundTradeHistoryRepository;
    }
}
