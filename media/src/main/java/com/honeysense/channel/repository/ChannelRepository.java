package com.honeysense.channel.repository;

import com.honeysense.channel.entity.Channel;
import com.honeysense.magpie.saas.repository.MagpieRepository;

public interface ChannelRepository extends MagpieRepository<Channel> {
    Channel findByShortId(String shortId);
}
