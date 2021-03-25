package com.honeysense.magpie.medium.controller.c.driver;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.controller.c.driver.req.PostMediumDriverOrderReq;
import com.honeysense.magpie.medium.controller.c.driver.req.PostMediumDriverOrderVerifyReq;
import com.honeysense.magpie.medium.controller.c.driver.req.PutMediumDriverInfoReq;
import com.honeysense.magpie.medium.controller.c.driver.res.PostMediumDriverOrderRes;
import com.honeysense.magpie.medium.controller.c.driver.res.PostMediumDriverOrderVerifyRes;
import com.honeysense.magpie.medium.entity.MediumDriverInfo;
import com.honeysense.magpie.medium.entity.MediumDriverOrder;
import com.honeysense.magpie.medium.entity.MediumDriverOrderStatus;
import com.honeysense.magpie.medium.entity.MediumDriverOrderVerify;
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

@Api(tags = "用户 - 司机")
@RestController
@RequestMapping("c/{channel}/driver")
public class DriverMediumController {
    @Autowired
    private MediumDriverInfoService mediumDriverInfoService;
    @Autowired
    private MediumDriverOrderService mediumDriverOrderService;
    @Autowired
    private MediumDriverOrderVerifyService mediumDriverOrderVerifyService;

    @ApiOperation(value = "获取司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("info")
    @ResponseBody
    public MediumDriverInfo getInfo(@ApiParam(value = "渠道 ID", hidden = true)
                                    @RequestAttribute("channelId") Long channelId,
                                    @MagpieAnnotationToken MagpieToken magpieToken) {
        MediumDriverInfo mediumDriverInfo = mediumDriverInfoService.findByChannelIdAndUserId(channelId, magpieToken.getUserId());
        if (mediumDriverInfo == null) {
            mediumDriverInfo = new MediumDriverInfo();
        }

        return mediumDriverInfo;
    }

    @ApiOperation(value = "更新司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("info")
    @ResponseBody
    public void putInfo(@ApiParam(value = "渠道 ID", hidden = true)
                        @RequestAttribute("channelId") Long channelId,
                        @ApiParam(value = "用户令牌", required = true, hidden = true)
                        @MagpieAnnotationToken MagpieToken magpieToken,
                        @ApiParam(value = "传入参数", required = true)
                        @Validated @RequestBody PutMediumDriverInfoReq req) {
        MediumDriverInfo mediumDriverInfo = mediumDriverInfoService.findByChannelIdAndUserId(channelId, magpieToken.getUserId());
        if (mediumDriverInfo == null) {
            mediumDriverInfo = new MediumDriverInfo();
            mediumDriverInfo.setUserId(magpieToken.getUserId());
        }

        mediumDriverInfo.setName(req.getName());
        mediumDriverInfo.setContract(req.getContract());
        mediumDriverInfo.setAvatar(req.getAvatar());
        mediumDriverInfo.setCarHailingPlatform(req.getCarHailingPlatform());
        mediumDriverInfo.setCarHailingLicenseImage(req.getCarHailingLicenseImage());
        mediumDriverInfo.setPlateNumber(req.getPlateNumber());
        mediumDriverInfo.setCarImage(req.getCarImage());
        mediumDriverInfo.setCarHailingLicenseImage(req.getCarHailingLicenseImage());

        mediumDriverInfoService.save(mediumDriverInfo);
    }

    @ApiOperation(value = "司机接受投放的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("order")
    @ResponseBody
    public PostMediumDriverOrderRes postServingOrder(@ApiParam(value = "渠道 ID", hidden = true)
                                                     @RequestAttribute("channelId") Long channelId,
                                                     @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                     @MagpieAnnotationToken MagpieToken magpieToken,
                                                     @ApiParam(value = "传入参数", required = true)
                                                     @Validated @RequestBody PostMediumDriverOrderReq req) {
        MediumDriverOrderStatus[] statuses = new MediumDriverOrderStatus[]{
                MediumDriverOrderStatus.CANCEL, MediumDriverOrderStatus.FINISH
        };

        MagpiePage<MediumDriverOrder> servingOrderMagpiePage = mediumDriverOrderService.findAllByChannelIdAndUserIdAndOrderStatusNotIn(channelId, magpieToken.getUserId(), statuses, new MagpiePageRequest(0, 1));
        if (servingOrderMagpiePage.getTotalSize() > 0) {
            return PostMediumDriverOrderRes.builder().driverOrderProcessing(true).build();
        }

        MediumDriverOrder mediumDriverOrder = mediumDriverOrderService.insertOne(req.getServingAuctionId(), channelId, magpieToken.getUserId(),
                req.getSendAddress(), req.getSendUser(), req.getSendPhone());

        return PostMediumDriverOrderRes.builder().driverOrderId(mediumDriverOrder.getId()).build();
    }

    @ApiOperation(value = "获取正在进行中的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order/live")
    @ResponseBody
    public MediumDriverOrder getServingOrderLive(@ApiParam(value = "渠道 ID", hidden = true)
                                                 @RequestAttribute("channelId") Long channelId,
                                                 @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                 @MagpieAnnotationToken MagpieToken magpieToken) {
        MediumDriverOrderStatus[] statuses = new MediumDriverOrderStatus[]{
                MediumDriverOrderStatus.CANCEL, MediumDriverOrderStatus.FINISH
        };

        MagpiePage<MediumDriverOrder> servingOrderMagpiePage = mediumDriverOrderService.findAllByChannelIdAndUserIdAndOrderStatusNotIn(channelId, magpieToken.getUserId(), statuses, new MagpiePageRequest(0, 1));
        if (servingOrderMagpiePage.getTotalSize() > 0) {
            return servingOrderMagpiePage.getElements().get(0);
        }

        return null;
    }

    @ApiOperation(value = "获取所有的广告订单", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("order")
    @ResponseBody
    public MagpiePage<MediumDriverOrder> getServingOrder(@ApiParam(value = "渠道 ID", hidden = true)
                                                         @RequestAttribute("channelId") Long channelId,
                                                         @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                         @MagpieAnnotationToken MagpieToken magpieToken,
                                                         @ApiParam(value = "分页对象")
                                                         @Validated MagpiePageRequest magpiePageRequest) {
        return mediumDriverOrderService.findAllByChannelIdAndUserId(channelId, magpieToken.getUserId(), magpiePageRequest);
    }

    @ApiOperation(value = "提交广告订单审核", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("order/{driverOrderId}/certify")
    @ResponseBody
    public PostMediumDriverOrderVerifyRes getServingOrderCertify(@ApiParam(value = "渠道 ID", hidden = true)
                                                                 @RequestAttribute("channelId") Long channelId,
                                                                 @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                                 @MagpieAnnotationToken MagpieToken magpieToken,
                                                                 @ApiParam(value = "司机接受投放的广告订单 ID", hidden = true)
                                                                 @PathVariable("driverOrderId") Long driverOrderId,
                                                                 @ApiParam(value = "传入参数", required = true)
                                                                 @Validated @RequestBody PostMediumDriverOrderVerifyReq req) {
        MediumDriverOrder mediumDriverOrder = mediumDriverOrderService.findByChannelIdAndId(channelId, driverOrderId);
        if (mediumDriverOrder == null) {
            return PostMediumDriverOrderVerifyRes.builder().driverOrderIdNotExists(true).build();
        }

        MediumDriverOrderStatus status;

        switch (mediumDriverOrder.getStatus()) {
            case APPLYING:
            case APPLY_REFUSE:
            case CANCEL:
            case FINISH:
            case CERTIFY_THIRD_VERIFY:
            default:
                return PostMediumDriverOrderVerifyRes.builder().stateError(true).build();
            case MATERIAL_SEND:
            case CERTIFY_FIRST_FAILED:
            case CERTIFY_FIRST_UPLOAD:
                status = MediumDriverOrderStatus.CERTIFY_FIRST_UPLOAD;
                break;
            case CERTIFY_FIRST_VERIFY:
            case CERTIFY_SECOND_FAILED:
            case CERTIFY_SECOND_UPLOAD:
                status = MediumDriverOrderStatus.CERTIFY_SECOND_UPLOAD;
                break;
            case CERTIFY_SECOND_VERIFY:
            case CERTIFY_THIRD_FAILED:
            case CERTIFY_THIRD_UPLOAD:
                status = MediumDriverOrderStatus.CERTIFY_THIRD_UPLOAD;
                break;
        }

        MediumDriverOrderVerify mediumDriverOrderVerify = MediumDriverOrderVerify.builder()
                .channelId(channelId)
                .userId(magpieToken.getUserId())
                .mediumDriverOrder(mediumDriverOrder)
                .status(status)
                .image1(req.getImage1())
                .image2(req.getImage2())
                .image3(req.getImage3())
                .remark(req.getRemark())
                .build();

        mediumDriverOrderVerifyService.save(mediumDriverOrderVerify);

        return PostMediumDriverOrderVerifyRes.builder().build();
    }


}
