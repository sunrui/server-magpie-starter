package com.honeysense.magpie.user.controller.c;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.ip.MagpieAnnotationIp;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.framework.spring.annotation.ua.MagpieAnnotationUa;
import com.honeysense.magpie.framework.utils.MagpieJwt;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.framework.utils.format.MagpieTimeFormat;
import com.honeysense.magpie.sms.entity.SmsCodeType;
import com.honeysense.magpie.sms.service.SmsCodeService;
import com.honeysense.magpie.user.controller.c.req.PostLoginPasswordReq;
import com.honeysense.magpie.user.controller.c.req.PostLoginPhoneReq;
import com.honeysense.magpie.user.controller.c.req.PostRegisterReq;
import com.honeysense.magpie.user.controller.c.res.*;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.entity.UserLoginHistory;
import com.honeysense.magpie.user.entity.UserRelation;
import com.honeysense.magpie.user.entity.refer.UserRefer;
import com.honeysense.magpie.user.service.UserLoginHistoryService;
import com.honeysense.magpie.user.service.UserRelationService;
import com.honeysense.magpie.user.service.UserService;
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
    private UserLoginHistoryService userLoginHistoryService;
    private final MagpieJwt magpieJwt = new MagpieJwt();

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

        // 写入登录历史
        Date expiredAt = new Date(System.currentTimeMillis() + maxAge * 1000L);
        UserLoginHistory userLoginHistory = new UserLoginHistory(userRefer);
        userLoginHistory.setUserId(userId);
        userLoginHistory.setType(type);
        userLoginHistory.setExpiredAt(expiredAt);
        userLoginHistoryService.save(userLoginHistory);

        // 生成令牌对象
        MagpieToken magpieToken = MagpieToken.builder()
                .userId(userId)
                .type(type)
                .ip(userRefer.getIp())
                .createdAt(new Date())
                .expiredAt(expiredAt)
                .build();

        // 写入 cookie
        Cookie cookie = new Cookie(COOKIE_JWT_KEY, magpieJwt.sign(magpieToken));
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    @ApiOperation(value = "用户 - 注册 - 用户名/密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "register")
    @ResponseBody
    public PostRegisterRes postRegister(@ApiParam(value = "用户 IP", hidden = true)
                                        @MagpieAnnotationIp String ip,
                                        @ApiParam(value = "用户 UA", hidden = true)
                                        @MagpieAnnotationUa String userAgent,
                                        @ApiParam(value = "传入参数", required = true)
                                        @Validated @RequestBody PostRegisterReq req,
                                        HttpServletResponse httpServletResponse) {
        // 查询用户是否存在
        User user = userService.findByName(req.getUserName());
        if (user != null) {
            return PostRegisterRes.builder().userNameExists(true).build();
        }

        // 查询直推用户 ID 是否正确
        if (MagpieValidator.longId(req.getRefer().getDirectInvitorUserId())) {
            UserRelation userRelation = userRelationService.findByUserId(req.getRefer().getDirectInvitorUserId());
            if (userRelation == null) {
                return PostRegisterRes.builder().directInvitorUserIdNotExists(true).build();
            }
        }

        // 判断摩术码是否正确
        if (!"magpie".equals(req.getMagic())) {
            return PostRegisterRes.builder().magicError(true).build();
        }

        // 生成用户来源
        UserRefer userRefer = req.getRefer().toUserRefer(ip, userAgent);

        // 注册新用户
        user = userService.insertName(req.getUserName(), req.getPassword(), userRefer);

        // 登录成功
        return PostRegisterRes.builder().user(user).build();
    }

    @ApiOperation(value = "用户 - 登录 - 手机", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/phone")
    @ResponseBody
    public PostLoginPhoneRes postLoginPhone(@ApiParam(value = "用户 IP", hidden = true)
                                            @MagpieAnnotationIp String ip,
                                            @ApiParam(value = "用户 UA", hidden = true)
                                            @MagpieAnnotationUa String userAgent,
                                            @ApiParam(value = "传入参数", required = true)
                                            @Validated @RequestBody PostLoginPhoneReq req,
                                            HttpServletResponse httpServletResponse) {
        // 较验手机号是否存在验证码
        boolean haveValidSmsCode = smsCodeService.validPhoneSmsCode(req.getPhone());
        if (!haveValidSmsCode) {
            return PostLoginPhoneRes.builder().smsCodeSendNeeded(true).build();
        }

        // 较验手机号验证码是否正确
        boolean verifyOk = smsCodeService.validPhoneAndSmsCodeAndSmsCodeType(req.getPhone(), req.getSmsCode(), SmsCodeType.LOGIN);
        if (!verifyOk) {
            return PostLoginPhoneRes.builder().smsCodeVerifyError(true).build();
        }

        // 判断直推用户 ID 是否存在
        if (MagpieValidator.longId(req.getRefer().getDirectInvitorUserId())) {
            UserRelation userRelation = userRelationService.findByUserId(req.getRefer().getDirectInvitorUserId());
            if (userRelation == null) {
                return PostLoginPhoneRes.builder().directInvitorUserIdNotExists(true).build();
            }
        }

        // 生成用户来源
        UserRefer userRefer = req.getRefer().toUserRefer(ip, userAgent);

        // 查找用户是否已经存在
        User user = userService.findByPhone(req.getPhone());
        if (user == null) {
            // 新的用户
            user = userService.insertPhone(req.getPhone(), userRefer);
        }

        // 写入记录历史
        UserLoginHistory userLoginHistory = new UserLoginHistory(userRefer);
        userLoginHistory.setUserId(user.getId());
        userLoginHistory.setDay(MagpieTimeFormat.makeToday());
        userLoginHistory.setExpiredAt(new Date(System.currentTimeMillis() + req.getMaxAge() * 1000L));
        userLoginHistory.setSuccess(true);
        userLoginHistory.setType(MagpieToken.MagpieTokenType.PHONE);
        userLoginHistoryService.save(userLoginHistory);

        // 写入令牌
        writeToken(MagpieToken.MagpieTokenType.PHONE, req.getMaxAge(), user.getId(), userRefer, httpServletResponse);

        return PostLoginPhoneRes.builder().user(user).build();
    }

    @ApiOperation(value = "用户 - 登录 - 密码", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/password")
    @ResponseBody
    public PostLoginPasswordRes postLoginPassword(@ApiParam(value = "用户 IP", hidden = true)
                                                  @MagpieAnnotationIp String ip,
                                                  @ApiParam(value = "用户 UA", hidden = true)
                                                  @MagpieAnnotationUa String userAgent,
                                                  @ApiParam(value = "传入参数", required = true)
                                                  @Validated @RequestBody PostLoginPasswordReq req,
                                                  HttpServletResponse httpServletResponse) {
        // 查询用户是否存在
        User user = userService.findByName(req.getUserName());
        if (user == null) {
            return PostLoginPasswordRes.builder().userNameNotExists(true).build();
        }

        // 查询直推用户 ID 是否正确
        if (MagpieValidator.longId(req.getRefer().getDirectInvitorUserId())) {
            UserRelation userRelation = userRelationService.findByUserId(req.getRefer().getDirectInvitorUserId());
            if (userRelation == null) {
                return PostLoginPasswordRes.builder().directInvitorUserIdNotExists(true).build();
            }
        }

        // 查询用户是否频繁登录
        int userLoginHistoryFailedCount = userLoginHistoryService.countAllByUserIdAndDayAndSuccess(user.getId(), MagpieTimeFormat.makeToday(), false);
        if (userLoginHistoryFailedCount > 10) {
            return PostLoginPasswordRes.builder().passwordLocked(true).build();
        }

        // 查询用户密码是否正确
        boolean validUserIdAndPassword = userService.validUserIdAndPassword(user.getId(), req.getPassword());

        // 生成用户来源
        UserRefer userRefer = req.getRefer().toUserRefer(ip, userAgent);

        // 记录登录历史
        UserLoginHistory userLoginHistory = new UserLoginHistory(userRefer);
        userLoginHistory.setUserId(user.getId());
        userLoginHistory.setDay(MagpieTimeFormat.makeToday());
        userLoginHistory.setExpiredAt(new Date(System.currentTimeMillis() + req.getMaxAge() * 1000L));
        userLoginHistory.setSuccess(validUserIdAndPassword);
        userLoginHistory.setType(MagpieToken.MagpieTokenType.PHONE);
        userLoginHistoryService.save(userLoginHistory);

        // 密码不正确
        if (!validUserIdAndPassword) {
            return PostLoginPasswordRes.builder().passwordVerifyError(true).build();
        }

        // 登录成功
        writeToken(MagpieToken.MagpieTokenType.PASSWORD, req.getMaxAge(), user.getId(), userRefer, httpServletResponse);
        return PostLoginPasswordRes.builder().user(user).build();
    }

    @ApiOperation(value = "用户 - 登录 - 微信小程序 - 获取 CODE", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/wechat/applet/code")
    @ResponseBody
    public PostWechatAppletCodeRes postWechatAppletCode(@ApiParam(value = "用户 IP", hidden = true)
                                                        @MagpieAnnotationIp String ip,
                                                        @ApiParam(value = "用户 UA", hidden = true)
                                                        @MagpieAnnotationUa String userAgent,
                                                        @ApiParam(value = "传入参数", required = true)
                                                        @Validated @RequestBody PostLoginPhoneReq req,
                                                        HttpServletResponse httpServletResponse) {
        throw new MagpieException(MagpieException.Type.SERVICE_UNAVAILABLE, "not implement");
//        return PostWechatAppletCodeRes.builder().build();
    }

    @ApiOperation(value = "用户 - 登录 - 微信小程序 - 获取手机号", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "login/wechat/applet/mobile")
    @ResponseBody
    public PostWechatAppletMobileRes postWechatAppletMobile(@ApiParam(value = "用户 IP", hidden = true)
                                                            @MagpieAnnotationIp String ip,
                                                            @ApiParam(value = "用户 UA", hidden = true)
                                                            @MagpieAnnotationUa String userAgent,
                                                            @ApiParam(value = "传入参数", required = true)
                                                            @Validated @RequestBody PostLoginPhoneReq req,
                                                            HttpServletResponse httpServletResponse) {
        throw new MagpieException(MagpieException.Type.SERVICE_UNAVAILABLE, "not implement");
//        return PostWechatAppletMobileRes.builder().build();
    }

    @ApiOperation(value = "用户 - 登录 - 历史记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "login/history")
    @ResponseBody
    public MagpiePage<UserLoginHistory> getLoginHistory(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                        @MagpieAnnotationToken MagpieToken magpieToken,
                                                        @ApiParam(value = "分页对象")
                                                        @Validated MagpiePageRequest magpiePageRequest) {
        return userLoginHistoryService.findAllByUserId(magpieToken.getUserId(), magpiePageRequest);
    }

    @ApiOperation(value = "用户 - 状态", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @ApiOperation(value = "用户 - 登出", produces = MediaType.APPLICATION_JSON_VALUE)
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

}
