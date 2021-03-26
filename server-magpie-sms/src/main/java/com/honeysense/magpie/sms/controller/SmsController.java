package com.honeysense.magpie.sms.controller;

import com.honeysense.magpie.framework.spring.annotation.ip.MagpieAnnotationIp;
import com.honeysense.magpie.framework.spring.annotation.ua.MagpieAnnotationUa;
import com.honeysense.magpie.framework.utils.format.MagpieTimeFormat;
import com.honeysense.magpie.sms.controller.req.PostSmsSendReq;
import com.honeysense.magpie.sms.controller.req.PostSmsVerifyReq;
import com.honeysense.magpie.sms.controller.res.PostSmsVerifyRes;
import com.honeysense.magpie.sms.entity.SmsCode;
import com.honeysense.magpie.sms.service.SmsCodeService;
import com.honeysense.magpie.sms.controller.res.PostSmsSendRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "验证码")
@RestController
@RequestMapping("sms")
public class SmsController {
    @Autowired
    private SmsCodeService smsCodeService;

    @Value("${spring.profiles.active}")
    private String profile;

    @ApiOperation(value = "验证码 - 发送", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "send")
    @ResponseBody
    public PostSmsSendRes postSend(@ApiParam(value = "用户 IP", hidden = true)
                                   @MagpieAnnotationIp String ip,
                                   @ApiParam(value = "用户 UA", hidden = true)
                                   @MagpieAnnotationUa String userAgent,
                                   @ApiParam(value = "传入参数", required = true)
                                   @Validated @RequestBody PostSmsSendReq req) {
        Long today = MagpieTimeFormat.getToday();

        int count = smsCodeService.countAllByPhoneAndDay(req.getPhone(), today);
        if (count >= SmsCode.MAX_SEND_PER_DAY) {
            return PostSmsSendRes.builder().maxSendReached(true).build();
        }

        int MAX_EXPIRED_MILLI_SECONDS = 15 * 60 * 1000;

        SmsCode smsCode = SmsCode.builder()
                .channelId(1000L)
                .type(req.getSmsCodeType())
                .phone(req.getPhone())
                .code(smsCodeService.generateRandomSmsCode())
                .day(today)
                .verifyTimes(0)
                .ip(ip)
                .userAgent(userAgent)
                .expiredAt(new Date(System.currentTimeMillis() + MAX_EXPIRED_MILLI_SECONDS))
                .build();
        smsCodeService.save(smsCode);

        // TODO MQ SEND

        if (profile.contentEquals("dev")) {
            return PostSmsSendRes.builder().success(true).smsCode(smsCode.getCode()).build();
        }

        return PostSmsSendRes.builder().success(true).build();
    }

    @ApiOperation(value = "验证码 - 验证", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "verify")
    @ResponseBody
    public PostSmsVerifyRes postVerify(@ApiParam(value = "传入参数", required = true)
                                       @Validated @RequestBody PostSmsVerifyReq req) {
        boolean haveValidSmsCode = smsCodeService.weatherHaveValidSmsCode(req.getPhone());
        if (!haveValidSmsCode) {
            return PostSmsVerifyRes.builder().codeNotExists(true).build();
        }

        boolean verifyOk = smsCodeService.weatherVerifyOk(req.getPhone(), req.getCode(), req.getSmsCodeType());
        if (verifyOk) {
            return PostSmsVerifyRes.builder().verifyOk(true).build();
        }

        return PostSmsVerifyRes.builder().verifyError(true).build();
    }
}
