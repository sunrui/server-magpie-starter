package com.honeysense.media.controller.c.driver;

import com.honeysense.media.controller.c.driver.req.PostDriverOrderReq;
import com.honeysense.media.controller.c.driver.req.PostDriverOrderVerifyReq;
import com.honeysense.media.controller.c.driver.req.PutDriverInfoReq;
import com.honeysense.media.controller.c.driver.res.PostDriverOrderRes;
import com.honeysense.media.controller.c.driver.res.PostDriverOrderVerifyRes;
import com.honeysense.media.entity.DriverInfo;
import com.honeysense.media.entity.DriverOrder;
import com.honeysense.media.entity.DriverOrderStatus;
import com.honeysense.media.entity.DriverOrderVerify;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户 - 司机")
@RestController
@RequestMapping("c/{channel}/driver")
public class DriverController {
    @Autowired
    private DriverInfoService driverInfoService;
    @Autowired
    private DriverOrderService driverOrderService;
    @Autowired
    private DriverOrderVerifyService driverOrderVerifyService;

    @ApiOperation(value = "获取司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    public DriverInfo getInfo(@ApiParam(value = "渠道 ID", hidden = true)
                              @RequestAttribute("channelId") Long channelId,
                              @MagpieAnnotationToken MagpieToken magpieToken) {
        DriverInfo driverInfo = driverInfoService.findByChannelIdAndUserId(channelId, magpieToken.getUserId());
        if (driverInfo == null) {
            driverInfo = new DriverInfo();
        }

        return driverInfo;
    }

    @ApiOperation(value = "更新司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("info")
    @ResponseBody
    public void putInfo(@ApiParam(value = "渠道 ID", hidden = true)
                        @RequestAttribute("channelId") Long channelId,
                        @ApiParam(value = "用户令牌", required = true, hidden = true)
                        @MagpieAnnotationToken MagpieToken magpieToken,
                        @ApiParam(value = "传入参数", required = true)
                        @Validated @RequestBody PutDriverInfoReq req) {
        DriverInfo driverInfo = driverInfoService.findByChannelIdAndUserId(channelId, magpieToken.getUserId());
        if (driverInfo == null) {
            driverInfo = new DriverInfo();
            driverInfo.setUserId(magpieToken.getUserId());
        }

        driverInfo.setName(req.getName());
        driverInfo.setContract(req.getContract());
        driverInfo.setAvatar(req.getAvatar());
        driverInfo.setCarHailingPlatform(req.getCarHailingPlatform());
        driverInfo.setCarHailingLicenseImage(req.getCarHailingLicenseImage());
        driverInfo.setPlateNumber(req.getPlateNumber());
        driverInfo.setCarImage(req.getCarImage());
        driverInfo.setCarHailingLicenseImage(req.getCarHailingLicenseImage());

        driverInfoService.save(driverInfo);
    }

    @ApiOperation(value = "司机接受投放的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("order")
    @ResponseBody
    public PostDriverOrderRes postServingOrder(@ApiParam(value = "渠道 ID", hidden = true)
                                               @RequestAttribute("channelId") Long channelId,
                                               @ApiParam(value = "用户令牌", required = true, hidden = true)
                                               @MagpieAnnotationToken MagpieToken magpieToken,
                                               @ApiParam(value = "传入参数", required = true)
                                               @Validated @RequestBody PostDriverOrderReq req) {
        DriverOrderStatus[] statuses = new DriverOrderStatus[]{
                DriverOrderStatus.CANCEL, DriverOrderStatus.FINISH
        };

        MagpiePage<DriverOrder> servingOrderMagpiePage = driverOrderService.findAllByChannelIdAndUserIdAndOrderStatusNotIn(channelId, magpieToken.getUserId(), statuses, 0, 1);
        if (servingOrderMagpiePage.getTotalSize() > 0) {
            return PostDriverOrderRes.builder().driverOrderProcessing(true).build();
        }

        DriverOrder driverOrder = driverOrderService.insertOne(req.getServingAuctionId(), channelId, magpieToken.getUserId(),
                req.getSendAddress(), req.getSendUser(), req.getSendPhone());

        return PostDriverOrderRes.builder().driverOrderId(driverOrder.getId()).build();
    }

    @ApiOperation(value = "获取正在进行中的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order/live")
    @ResponseBody
    public DriverOrder getServingOrderLive(@ApiParam(value = "渠道 ID", hidden = true)
                                           @RequestAttribute("channelId") Long channelId,
                                           @ApiParam(value = "用户令牌", required = true, hidden = true)
                                           @MagpieAnnotationToken MagpieToken magpieToken) {
        DriverOrderStatus[] statuses = new DriverOrderStatus[]{
                DriverOrderStatus.CANCEL, DriverOrderStatus.FINISH
        };

        MagpiePage<DriverOrder> servingOrderMagpiePage = driverOrderService.findAllByChannelIdAndUserIdAndOrderStatusNotIn(channelId, magpieToken.getUserId(), statuses, 0, 1);
        if (servingOrderMagpiePage.getTotalSize() > 0) {
            return servingOrderMagpiePage.getElements().get(0);
        }

        return null;
    }

    @ApiOperation(value = "获取所有的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order")
    @ResponseBody
    public MagpiePage<DriverOrder> getServingOrder(@ApiParam(value = "渠道 ID", hidden = true)
                                                   @RequestAttribute("channelId") Long channelId,
                                                   @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                   @MagpieAnnotationToken MagpieToken magpieToken,
                                                   @ApiParam(value = "第几页")
                                                   @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                   @ApiParam(value = "页大小")
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return driverOrderService.findAllByChannelIdAndUserId(channelId, magpieToken.getUserId(), page, size);
    }

    @ApiOperation(value = "提交广告订单审核", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("order/{driverOrderId}/certify")
    @ResponseBody
    public PostDriverOrderVerifyRes getServingOrderCertify(@ApiParam(value = "渠道 ID", hidden = true)
                                                           @RequestAttribute("channelId") Long channelId,
                                                           @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                           @MagpieAnnotationToken MagpieToken magpieToken,
                                                           @ApiParam(value = "司机接受投放的广告订单 ID", hidden = true)
                                                           @PathVariable("driverOrderId") Long driverOrderId,
                                                           @ApiParam(value = "传入参数", required = true)
                                                           @Validated @RequestBody PostDriverOrderVerifyReq req) {
        DriverOrder driverOrder = driverOrderService.findByChannelIdAndId(channelId, driverOrderId);
        if (driverOrder == null) {
            return PostDriverOrderVerifyRes.builder().driverOrderIdNotExists(true).build();
        }

        DriverOrderStatus status;

        switch (driverOrder.getStatus()) {
            case APPLYING:
            case APPLY_REFUSE:
            case CANCEL:
            case FINISH:
            case CERTIFY_THIRD_VERIFY:
            default:
                return PostDriverOrderVerifyRes.builder().stateError(true).build();
            case MATERIAL_SEND:
            case CERTIFY_FIRST_FAILED:
            case CERTIFY_FIRST_UPLOAD:
                status = DriverOrderStatus.CERTIFY_FIRST_UPLOAD;
                break;
            case CERTIFY_FIRST_VERIFY:
            case CERTIFY_SECOND_FAILED:
            case CERTIFY_SECOND_UPLOAD:
                status = DriverOrderStatus.CERTIFY_SECOND_UPLOAD;
                break;
            case CERTIFY_SECOND_VERIFY:
            case CERTIFY_THIRD_FAILED:
            case CERTIFY_THIRD_UPLOAD:
                status = DriverOrderStatus.CERTIFY_THIRD_UPLOAD;
                break;
        }

        DriverOrderVerify driverOrderVerify = DriverOrderVerify.builder()
                .channelId(channelId)
                .userId(magpieToken.getUserId())
                .driverOrder(driverOrder)
                .status(status)
                .image1(req.getImage1())
                .image2(req.getImage2())
                .image3(req.getImage3())
                .remark(req.getRemark())
                .build();

        driverOrderVerifyService.save(driverOrderVerify);

        return PostDriverOrderVerifyRes.builder().build();
    }


}
