package com.honeysense.magpie.medium.controller.b.driver;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumDriverInfo;
import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.service.MediumDriverInfoService;
import com.honeysense.magpie.medium.service.MediumDriverOrderService;
import com.honeysense.magpie.medium.service.MediumDriverOrderVerifyService;
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

@Api(tags = "后台 - 司机")
@RestController
@RequestMapping("b/{channel}/driver")
public class MediumDriverAdminController {
    @Autowired
    private MediumDriverInfoService mediumDriverInfoService;
    @Autowired
    private MediumDriverOrderService mediumDriverOrderService;
    @Autowired
    private MediumDriverOrderVerifyService mediumDriverOrderVerifyService;

    @ApiOperation(value = "获取所有的司机基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    MagpiePage<MediumDriverInfo> getAllDriverInfo(@ApiParam(value = "开发者 ID", hidden = true)
                                                  @RequestAttribute("appId") Long appId,
                                                  @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                  @MagpieAnnotationToken MagpieToken magpieToken,
                                                  @ApiParam(value = "分页对象")
                                                  @Validated MagpiePageRequest magpiePageRequest) {
        return mediumDriverInfoService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取所有的司机订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order")
    @ResponseBody
    MagpiePage<MediumDriverOrder> getAllDriverOrder(@ApiParam(value = "开发者 ID", hidden = true)
                                                    @RequestAttribute("appId") Long appId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                    @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "分页对象")
                                                    @Validated MagpiePageRequest magpiePageRequest) {
        return mediumDriverOrderService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取某个的司机基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/info")
    @ResponseBody
    MediumDriverInfo getDriverInfoByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                           @RequestAttribute("appId") Long appId,
                                           @ApiParam(value = "用户 ID", required = true, hidden = true)
                                           @PathVariable("userId") Long userId) {
        return mediumDriverInfoService.findByAppIdAndUserId(appId, userId);
    }

    @ApiOperation(value = "获取某个的司机订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/order")
    @ResponseBody
    MagpiePage<MediumDriverOrder> getAllDriverOrderByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                                            @RequestAttribute("appId") Long appId,
                                                            @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                            @MagpieAnnotationToken MagpieToken magpieToken,
                                                            @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                            @PathVariable("userId") Long userId,
                                                            @ApiParam(value = "分页对象")
                                                            @Validated MagpiePageRequest magpiePageRequest) {
        return mediumDriverOrderService.findAllByAppIdAndUserId(appId, userId, magpiePageRequest);
    }
}
