package com.honeysense.magpie.channel.controller;

import com.honeysense.magpie.channel.entity.Channel;
import com.honeysense.magpie.channel.service.ChannelService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "渠道")
@RestController
@RequestMapping("channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @ApiOperation(value = "获取所有的渠道", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    MagpiePage<Channel> getAllChannel(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                      @MagpieAnnotationToken MagpieToken magpieToken,
                                      @ApiParam(value = "分页对象")
                                      @Validated MagpiePageRequest magpiePageRequest) {
        return channelService.findAll(magpiePageRequest);
    }
}
