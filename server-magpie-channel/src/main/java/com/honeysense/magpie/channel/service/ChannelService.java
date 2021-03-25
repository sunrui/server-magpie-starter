package com.honeysense.magpie.channel.service;

import com.honeysense.magpie.channel.entity.Channel;
import com.honeysense.magpie.framework.saas.service.MagpieService;

public interface ChannelService extends MagpieService<Channel> {
    Channel findByShortId(String shortId);
}
