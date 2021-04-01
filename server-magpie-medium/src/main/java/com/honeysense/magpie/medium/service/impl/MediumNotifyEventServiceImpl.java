package com.honeysense.magpie.medium.service.impl;

import com.honeysense.magpie.medium.entity.MediumNotifyEvent;
import com.honeysense.magpie.medium.repository.MediumNotifyEventRepository;
import com.honeysense.magpie.medium.service.MediumNotifyEventService;
import com.honeysense.magpie.framework.saas.service.impl.MagpieAppUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediumNotifyEventServiceImpl extends MagpieAppUserManyServiceImpl<MediumNotifyEvent> implements MediumNotifyEventService {
    private final MediumNotifyEventRepository mediumNotifyEventRepository;

    @Autowired
    protected MediumNotifyEventServiceImpl(MediumNotifyEventRepository mediumNotifyEventRepository) {
        super(mediumNotifyEventRepository);

        this.mediumNotifyEventRepository = mediumNotifyEventRepository;
    }
}
