package com.honeysense.magpie.medium.controller.c.sponsor;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumSponsorPublish;
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

@Api(tags = "用户 - 广告主")
@RestController
@RequestMapping("c/{channel}/sponsor")
public class MediumSponsorController {
    @Autowired
    private MediumSponsorPublishService mediumSponsorPublishService;

    @ApiOperation(value = "获取所有的广告主投放期数实例", produces = MediaType.APPLICATION_JSON_VALUE)
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
}
