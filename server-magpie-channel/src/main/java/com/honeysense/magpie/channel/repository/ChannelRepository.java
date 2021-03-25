package com.honeysense.magpie.channel.repository;

import com.honeysense.magpie.channel.entity.Channel;
import com.honeysense.magpie.framework.saas.repository.MagpieRepository;

public interface ChannelRepository extends MagpieRepository<Channel> {
    Channel findByShortId(String shortId);
}
