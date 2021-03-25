package com.honeysense.channel.service;

import com.honeysense.channel.entity.Channel;
import com.honeysense.magpie.saas.service.MagpieService;
import org.springframework.cache.annotation.Cacheable;

public interface ChannelService extends MagpieService<Channel> {
    Channel findByShortId(String shortId);
}
