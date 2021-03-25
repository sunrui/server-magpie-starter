package com.honeysense.media.controller.b.driver;

import com.honeysense.media.entity.DriverInfo;
import com.honeysense.media.entity.DriverOrder;
import com.honeysense.media.service.DriverInfoService;
import com.honeysense.media.service.DriverOrderService;
import com.honeysense.media.service.DriverOrderVerifyService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 司机")
@RestController
@RequestMapping("b/{channel}/driver")
public class DriverAdminController {
    @Autowired
    private DriverInfoService driverInfoService;
    @Autowired
    private DriverOrderService driverOrderService;
    @Autowired
    private DriverOrderVerifyService driverOrderVerifyService;

    @ApiOperation(value = "获取所有的司机基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    MagpiePage<DriverInfo> getAllDriverInfo(@ApiParam(value = "渠道 ID", hidden = true)
                                            @RequestAttribute("channelId") Long channelId,
                                            @ApiParam(value = "用户令牌", required = true, hidden = true)
                                            @MagpieAnnotationToken MagpieToken magpieToken,
                                            @ApiParam(value = "第几页")
                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                            @ApiParam(value = "页大小")
                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return driverInfoService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取所有的司机订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order")
    @ResponseBody
    MagpiePage<DriverOrder> getAllDriverOrder(@ApiParam(value = "渠道 ID", hidden = true)
                                              @RequestAttribute("channelId") Long channelId,
                                              @ApiParam(value = "用户令牌", required = true, hidden = true)
                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                              @ApiParam(value = "第几页")
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @ApiParam(value = "页大小")
                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return driverOrderService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取某个的司机基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/info")
    @ResponseBody
    DriverInfo getDriverInfoByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                     @RequestAttribute("channelId") Long channelId,
                                     @ApiParam(value = "用户 ID", required = true, hidden = true)
                                     @PathVariable("userId") Long userId) {
        return driverInfoService.findByChannelIdAndUserId(channelId, userId);
    }

    @ApiOperation(value = "获取某个的司机订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("{userId}/order")
    @ResponseBody
    MagpiePage<DriverOrder> getAllDriverOrderByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                                      @RequestAttribute("channelId") Long channelId,
                                                      @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                      @MagpieAnnotationToken MagpieToken magpieToken,
                                                      @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                      @PathVariable("userId") Long userId,
                                                      @ApiParam(value = "第几页")
                                                      @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                      @ApiParam(value = "页大小")
                                                      @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return driverOrderService.findAllByChannelIdAndUserId(channelId, userId, page, size);
    }
}
