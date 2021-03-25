package com.honeysense.magpie.medium.controller.b.notify;

import com.honeysense.magpie.medium.entity.MediumNotifyEvent;
import com.honeysense.magpie.medium.service.MediumNotifyEventService;
import com.honeysense.magpie.framework.entity.MagpiePage;
import com.honeysense.magpie.framework.entity.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 通知")
@RestController
@RequestMapping("b/{channel}/notify")
public class MediumNotifyAdminController {
    @Autowired
    private MediumNotifyEventService mediumNotifyEventService;

    @ApiOperation(value = "获取所有的通知事件", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("event")
    @ResponseBody
    MagpiePage<MediumNotifyEvent> getAllNotifyEvent(@ApiParam(value = "渠道 ID", hidden = true)
                                              @RequestAttribute("channelId") Long channelId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "第几页")
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                    @ApiParam(value = "页大小")
                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return mediumNotifyEventService.findAllByChannelIdDesc(channelId, page, size);
    }
}
