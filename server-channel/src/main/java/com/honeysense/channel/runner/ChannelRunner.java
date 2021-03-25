package com.honeysense.channel.runner;

import com.honeysense.channel.entity.Channel;
import com.honeysense.channel.service.ChannelService;
import com.honeysense.magpie.entity.MagpiePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ChannelRunner implements ApplicationRunner {
    @Autowired
    private ChannelService channelService;

    @Override
    public void run(ApplicationArguments args) {
        MagpiePage<Channel> channelMagpiePage = channelService.findAll(0, 1);
        if (channelMagpiePage.getTotalSize() == 0) {
            Channel channel = Channel.builder()
                    .name("杭州")
                    .shortId("hz")
                    .build();
            channelService.save(channel);
        }
    }
}
