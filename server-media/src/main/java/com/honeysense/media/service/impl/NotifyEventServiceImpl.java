package com.honeysense.media.service.impl;

import com.honeysense.media.entity.NotifyEvent;
import com.honeysense.media.repository.NotifyEventRepository;
import com.honeysense.media.service.NotifyEventService;
import com.honeysense.magpie.saas.service.impl.MagpieChannelUserManyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyEventServiceImpl extends MagpieChannelUserManyServiceImpl<NotifyEvent> implements NotifyEventService {
    private final NotifyEventRepository notifyEventRepository;

    @Autowired
    protected NotifyEventServiceImpl(NotifyEventRepository notifyEventRepository) {
        super(notifyEventRepository);

        this.notifyEventRepository = notifyEventRepository;
    }
}
