package com.honeysense.user.controller.c;

import com.honeysense.magpie.config.MagpieConfig;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.ip.MagpieAnnotationIp;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.spring.annotation.ua.MagpieAnnotationUa;
import com.honeysense.magpie.utils.MagpieValidator;
import com.honeysense.sms.entity.SmsCodeType;
import com.honeysense.sms.service.SmsCodeService;
import com.honeysense.user.controller.c.req.PostLoginPhoneReq;
import com.honeysense.user.controller.c.res.PostLoginPhoneRes;
import com.honeysense.user.controller.c.res.PostWechatAppletCodeRes;
import com.honeysense.user.controller.c.res.PostWechatAppletMobileRes;
import com.honeysense.user.entity.User;
import com.honeysense.user.entity.UserLogin;
import com.honeysense.user.entity.UserRelation;
import com.honeysense.user.entity.refer.UserRefer;
import com.honeysense.user.service.UserLoginService;
import com.honeysense.user.service.UserRelationService;
import com.honeysense.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Api(tags = "用户")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private SmsCodeService smsCodeService;
    @Autowired
    private UserLoginService userLoginService;

    private static final String COOKIE_JWT_KEY = "token";
    private static final Integer TOKEN_EXPIRED_AT = 30 * 24 * 60 * 60;

    private void writeToken(MagpieToken.MagpieTokenType type,
                            Integer maxAge,
                            Long userId,
                            UserRefer userRefer,
                            HttpServletResponse httpServletResponse) {
        if (maxAge == null || maxAge <= 0) {
            maxAge = TOKEN_EXPIRED_AT;
        }

        Date expiredAt = new Date(System.currentTimeMillis() + maxAge * 1000L);

        UserLogin userLogin = new UserLogin(userRefer);
        userLogin.setUserId(userId);
        userLogin.setType(type);
        userLogin.setExpiredAt(expiredAt);
        userLoginService.save(userLogin);

        MagpieToken magpieToken = MagpieToken.builder()
                .userId(userId)
                .type(type)
                .ip(userRefer.getIp())
                .createdAt(new Date())
                .expiredAt(expiredAt)
                .build();

        Cookie cookie = new Cookie(COOKIE_JWT_KEY, MagpieConfig.jwt().sign(magpieToken));
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    @ApiOperation(value = "手机登录", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/phone")
    @ResponseBody
    public PostLoginPhoneRes postLoginPhone(@ApiParam(value = "用户 IP", hidden = true)
                                            @MagpieAnnotationIp String ip,
                                            @ApiParam(value = "用户 UA", hidden = true)
                                            @MagpieAnnotationUa String userAgent,
                                            @ApiParam(value = "传入参数", required = true)
                                            @Validated @RequestBody PostLoginPhoneReq req,
                                            HttpServletResponse httpServletResponse) {
        boolean haveValidSmsCode = smsCodeService.weatherHaveValidSmsCode(req.getPhone());
        if (!haveValidSmsCode) {
            return PostLoginPhoneRes.builder().smsCodeSendNeeded(true).build();
        }

        boolean verifyOk = smsCodeService.weatherVerifyOk(req.getPhone(), req.getSmsCode(), SmsCodeType.LOGIN);
        if (!verifyOk) {
            return PostLoginPhoneRes.builder().smsCodeVerifyError(true).build();
        }

        Long directInvitorUserId = null;
        Long indirectInvitorUserId = null;

        UserRefer userRefer = new UserRefer();
        if (MagpieValidator.longId(req.getRefer().getDirectInvitorUserId())) {
            UserRelation userRelation = userRelationService.findByUserId(req.getRefer().getDirectInvitorUserId());
            if (userRelation == null) {
                return PostLoginPhoneRes.builder().directInvitorUserIdNotExists(true).build();
            }

            directInvitorUserId = req.getRefer().getDirectInvitorUserId();

            if (MagpieValidator.longId(userRelation.getDirectInvitorUserId())) {
                indirectInvitorUserId = userRelation.getDirectInvitorUserId();
            }
        }

        userRefer.setChannelId(req.getRefer().getChannelId());
        userRefer.setDevice(req.getRefer().getDevice());
        userRefer.setDeviceImei(req.getRefer().getDeviceImei());
        userRefer.setDeviceVersion(req.getRefer().getDeviceVersion());
        userRefer.setIp(ip);
        userRefer.setUserAgent(userAgent);

        Optional<User> one = userService.findByPhone(req.getPhone());
        User user;
        if (one.isEmpty()) {
            user = userService.insertPhone(req.getPhone(), userRefer, directInvitorUserId, indirectInvitorUserId);
        } else {
            user = one.get();
        }

        writeToken(MagpieToken.MagpieTokenType.PHONE, req.getMaxAge(), user.getId(), userRefer, httpServletResponse);

        return PostLoginPhoneRes.builder().user(user).build();
    }

    @ApiOperation(value = "登出", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "logout")
    @ResponseBody
    public void postLogout(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contentEquals(COOKIE_JWT_KEY)) {
                    cookie.setValue(null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    httpServletResponse.addCookie(cookie);
                }
            }
        }
    }

    @ApiOperation(value = "微信登录获取 CODE", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/wechat/applet/code")
    @ResponseBody
    public PostWechatAppletCodeRes postWechatAppletCode(@ApiParam(value = "用户 IP", hidden = true)
                                                        @MagpieAnnotationIp String ip,
                                                        @ApiParam(value = "用户 UA", hidden = true)
                                                        @MagpieAnnotationUa String userAgent,
                                                        @ApiParam(value = "传入参数", required = true)
                                                        @Validated @RequestBody PostLoginPhoneReq req,
                                                        HttpServletResponse httpServletResponse) {
        return PostWechatAppletCodeRes.builder().build();
    }

    @ApiOperation(value = "微信登录获取手机号", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/wechat/applet/mobile")
    @ResponseBody
    public PostWechatAppletMobileRes postWechatAppletMobile(@ApiParam(value = "用户 IP", hidden = true)
                                                            @MagpieAnnotationIp String ip,
                                                            @ApiParam(value = "用户 UA", hidden = true)
                                                            @MagpieAnnotationUa String userAgent,
                                                            @ApiParam(value = "传入参数", required = true)
                                                            @Validated @RequestBody PostLoginPhoneReq req,
                                                            HttpServletResponse httpServletResponse) {
        return PostWechatAppletMobileRes.builder().build();
    }

    @ApiOperation(value = "获取登录历史", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "login/history")
    @ResponseBody
    public MagpiePage<UserLogin> getLoginHistory(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                 @MagpieAnnotationToken MagpieToken magpieToken,
                                                 @ApiParam(value = "第几页")
                                                 @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                 @ApiParam(value = "页大小")
                                                 @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {

        return userLoginService.findAllByUserId(magpieToken.getUserId(), page, size);
    }

    @ApiOperation(value = "获取用户状态", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "state")
    @ResponseBody
    public User getState(@ApiParam(value = "用户 IP", hidden = true)
                         @MagpieAnnotationIp String ip,
                         @ApiParam(value = "用户 UA", hidden = true)
                         @MagpieAnnotationUa String userAgent,
                         @MagpieAnnotationToken MagpieToken magpieToken) {
        User user = userService.findById(magpieToken.getUserId());
        if (user == null) {
            Map<String, Long> map = new HashMap<>();
            map.put("userId", magpieToken.getUserId());

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        return user;
    }
}
