package com.honeysense.magpie.channel.runner;

import com.honeysense.magpie.channel.entity.Channel;
import com.honeysense.magpie.channel.service.ChannelService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
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
        MagpiePage<Channel> channelMagpiePage = channelService.findAll(new MagpiePageRequest(0, 1));
        if (channelMagpiePage.getTotalSize() == 0) {
            Channel channel = Channel.builder()
                    .name("杭州")
                    .shortId("hz")
                    .build();
            channelService.save(channel);
        }
    }
}
