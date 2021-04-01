package com.honeysense.magpie.medium.controller.b.fund;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.entity.MediumFundAccount;
import com.honeysense.magpie.medium.entity.MediumFundTradeHistory;
import com.honeysense.magpie.medium.service.MediumFundAccountService;
import com.honeysense.magpie.medium.service.MediumFundTradeHistoryService;
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

@Api(tags = "后台 - 资金")
@RestController
@RequestMapping("b/{channel}/fund")
public class MediumFundAdminController {
    @Autowired
    private MediumFundAccountService mediumFundAccountService;
    @Autowired
    private MediumFundTradeHistoryService mediumFundTradeHistoryService;

    @ApiOperation(value = "获取所有的资金账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("account")
    @ResponseBody
    MagpiePage<MediumFundAccount> getAllFundAccount(@ApiParam(value = "开发者 ID", hidden = true)
                                                    @RequestAttribute("appId") Long appId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                    @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "分页对象")
                                                    @Validated MagpiePageRequest magpiePageRequest) {
        return mediumFundAccountService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取某个的资金账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("account/{userId}")
    @ResponseBody
    MediumFundAccount getAllAccountFundByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                                @RequestAttribute("appId") Long appId,
                                                @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                @MagpieAnnotationToken MagpieToken magpieToken,
                                                @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                @PathVariable("userId") Long userId) {
        return mediumFundAccountService.findByAppIdAndUserId(appId, userId);
    }

    @ApiOperation(value = "获取所有的资金账户交易记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("trade/history")
    @ResponseBody
    MagpiePage<MediumFundTradeHistory> getAllTradeHistory(@ApiParam(value = "开发者 ID", hidden = true)
                                                          @RequestAttribute("appId") Long appId,
                                                          @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                                          @ApiParam(value = "分页对象")
                                                          @Validated MagpiePageRequest magpiePageRequest) {
        return mediumFundTradeHistoryService.findAllByAppIdDesc(appId, magpiePageRequest);
    }

    @ApiOperation(value = "获取所有的资金账户交易记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("trade/{userId}/history")
    @ResponseBody
    MagpiePage<MediumFundTradeHistory> getAllTradeHistoryByUserId(@ApiParam(value = "开发者 ID", hidden = true)
                                                                  @RequestAttribute("appId") Long appId,
                                                                  @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                                  @MagpieAnnotationToken MagpieToken magpieToken,
                                                                  @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                                  @PathVariable("userId") Long userId,
                                                                  @ApiParam(value = "分页对象")
                                                                  @Validated MagpiePageRequest magpiePageRequest) {
        return mediumFundTradeHistoryService.findAllByAppIdAndUserId(appId, userId, magpiePageRequest);
    }
}
