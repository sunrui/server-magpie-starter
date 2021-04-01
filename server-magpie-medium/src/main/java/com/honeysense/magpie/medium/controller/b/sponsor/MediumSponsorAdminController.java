package com.honeysense.magpie.medium.controller.b.sponsor;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumSponsorInfo;
import com.honeysense.magpie.medium.entity.MediumSponsorPublish;
import com.honeysense.magpie.medium.service.MediumSponsorInfoService;
import com.honeysense.magpie.medium.service.MediumSponsorPublishMaterialsService;
import com.honeysense.magpie.medium.service.MediumSponsorPublishService;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 广告主")
@RestController
@RequestMapping("b/{channel}/sponsor")
public class MediumSponsorAdminController {
    @Autowired
    private MediumSponsorInfoService mediumSponsorInfoService;
    @Autowired
    private MediumSponsorPublishService mediumSponsorPublishService;
    @Autowired
    private MediumSponsorPublishMaterialsService mediumSponsorPublishMaterialsService;

    @ApiOperation(value = "获取所有的广告主基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    MagpiePage<MediumSponsorInfo> getAllSponsorInfo(@ApiParam(value = "开发者 ID", hidden = true)
                                                    @RequestAttribute("appId") Long appId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                    @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "分页对象")
                                                    @Validated MagpiePageRequest magpiePageRequest) {
        return mediumSponsorInfoService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取某个的广告主基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/info")
    @ResponseBody
    MediumSponsorInfo getAllSponsorInfoByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                                @RequestAttribute("appId") Long appId,
                                                @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                @MagpieAnnotationToken MagpieToken magpieToken,
                                                @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                @PathVariable("userId") Long userId,
                                                @ApiParam(value = "分页对象")
                                                @Validated MagpiePageRequest magpiePageRequest) {
        return mediumSponsorInfoService.findByAppIdAndUserId(appId, userId);
    }

    @ApiOperation(value = "获取所有的广告主发布", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("publish")
    @ResponseBody
    MagpiePage<MediumSponsorPublish> getAllSponsorPublish(@ApiParam(value = "开发者 ID", hidden = true)
                                                          @RequestAttribute("appId") Long appId,
                                                          @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                                          @ApiParam(value = "分页对象")
                                                          @Validated MagpiePageRequest magpiePageRequest) {
        return mediumSponsorPublishService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取某个的广告主发布", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/publish")
    @ResponseBody
    MagpiePage<MediumSponsorPublish> getAllSponsorPublishByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                                                  @RequestAttribute("appId") Long appId,
                                                                  @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                                  @MagpieAnnotationToken MagpieToken magpieToken,
                                                                  @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                                  @PathVariable("userId") Long userId,
                                                                  @ApiParam(value = "分页对象")
                                                                  @Validated MagpiePageRequest magpiePageRequest) {
        return mediumSponsorPublishService.findAllByAppIdAndUserId(appId, userId, magpiePageRequest);
    }
}
