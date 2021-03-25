package com.honeysense.magpie.channel.service.impl;

import com.honeysense.magpie.channel.entity.Channel;
import com.honeysense.magpie.channel.repository.ChannelRepository;
import com.honeysense.magpie.channel.service.ChannelService;
import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.saas.service.impl.MagpieServiceImpl;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelServiceImpl extends MagpieServiceImpl<Channel> implements ChannelService {
    private final ChannelRepository channelRepository;

    @Autowired
    public ChannelServiceImpl(ChannelRepository channelRepository) {
        super(channelRepository);

        this.channelRepository = channelRepository;
    }

    @Override
    public Channel findByShortId(String shortId) {
        if (!MagpieValidator.enId(shortId)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "shortId");
        }

        return channelRepository.findByShortId(shortId);
    }
}
