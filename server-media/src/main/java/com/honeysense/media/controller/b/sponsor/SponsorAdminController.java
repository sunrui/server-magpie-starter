package com.honeysense.media.controller.b.sponsor;

import com.honeysense.media.entity.SponsorInfo;
import com.honeysense.media.entity.SponsorPublish;
import com.honeysense.media.service.SponsorInfoService;
import com.honeysense.media.service.SponsorPublishMaterialsService;
import com.honeysense.media.service.SponsorPublishService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 广告主")
@RestController
@RequestMapping("b/{channel}/sponsor")
public class SponsorAdminController {
    @Autowired
    private SponsorInfoService sponsorInfoService;
    @Autowired
    private SponsorPublishService sponsorPublishService;
    @Autowired
    private SponsorPublishMaterialsService sponsorPublishMaterialsService;

    @ApiOperation(value = "获取所有的广告主基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    MagpiePage<SponsorInfo> getAllSponsorInfo(@ApiParam(value = "渠道 ID", hidden = true)
                                              @RequestAttribute("channelId") Long channelId,
                                              @ApiParam(value = "用户令牌", required = true, hidden = true)
                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                              @ApiParam(value = "第几页")
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @ApiParam(value = "页大小")
                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return sponsorInfoService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取某个的广告主基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/info")
    @ResponseBody
    SponsorInfo getAllSponsorInfoByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                          @RequestAttribute("channelId") Long channelId,
                                          @ApiParam(value = "用户令牌", required = true, hidden = true)
                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                          @ApiParam(value = "用户 ID", required = true, hidden = true)
                                          @PathVariable("userId") Long userId,
                                          @ApiParam(value = "第几页")
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                          @ApiParam(value = "页大小")
                                          @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return sponsorInfoService.findByChannelIdAndUserId(channelId, userId);
    }

    @ApiOperation(value = "获取所有的广告主发布", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("publish")
    @ResponseBody
    MagpiePage<SponsorPublish> getAllSponsorPublish(@ApiParam(value = "渠道 ID", hidden = true)
                                                    @RequestAttribute("channelId") Long channelId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                    @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "第几页")
                                                    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                    @ApiParam(value = "页大小")
                                                    @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return sponsorPublishService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取某个的广告主发布", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/publish")
    @ResponseBody
    MagpiePage<SponsorPublish> getAllSponsorPublishByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                                            @RequestAttribute("channelId") Long channelId,
                                                            @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                            @MagpieAnnotationToken MagpieToken magpieToken,
                                                            @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                            @PathVariable("userId") Long userId,
                                                            @ApiParam(value = "第几页")
                                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                            @ApiParam(value = "页大小")
                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return sponsorPublishService.findAllByChannelIdAndUserId(channelId, userId, page, size);
    }
}
