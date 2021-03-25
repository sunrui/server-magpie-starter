package com.honeysense.media.controller.b.fund;

import com.honeysense.media.entity.FundAccount;
import com.honeysense.media.entity.FundTradeHistory;
import com.honeysense.media.service.FundAccountService;
import com.honeysense.media.service.FundTradeHistoryService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 资金")
@RestController
@RequestMapping("b/{channel}/fund")
public class FundAdminController {
    @Autowired
    private FundAccountService fundAccountService;
    @Autowired
    private FundTradeHistoryService fundTradeHistoryService;

    @ApiOperation(value = "获取所有的资金账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("account")
    @ResponseBody
    MagpiePage<FundAccount> getAllFundAccount(@ApiParam(value = "渠道 ID", hidden = true)
                                              @RequestAttribute("channelId") Long channelId,
                                              @ApiParam(value = "用户令牌", required = true, hidden = true)
                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                              @ApiParam(value = "第几页")
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @ApiParam(value = "页大小")
                                              @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return fundAccountService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取某个的资金账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("account/{userId}")
    @ResponseBody
    FundAccount getAllAccountFundByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                          @RequestAttribute("channelId") Long channelId,
                                          @ApiParam(value = "用户令牌", required = true, hidden = true)
                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                          @ApiParam(value = "用户 ID", required = true, hidden = true)
                                          @PathVariable("userId") Long userId) {
        return fundAccountService.findByChannelIdAndUserId(channelId, userId);
    }

    @ApiOperation(value = "获取所有的资金账户交易记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("trade/history")
    @ResponseBody
    MagpiePage<FundTradeHistory> getAllTradeHistory(@ApiParam(value = "渠道 ID", hidden = true)
                                                    @RequestAttribute("channelId") Long channelId,
                                                    @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                    @MagpieAnnotationToken MagpieToken magpieToken,
                                                    @ApiParam(value = "第几页")
                                                    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                    @ApiParam(value = "页大小")
                                                    @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return fundTradeHistoryService.findAllByChannelIdDesc(channelId, page, size);
    }

    @ApiOperation(value = "获取所有的资金账户交易记录", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("trade/{userId}/history")
    @ResponseBody
    MagpiePage<FundTradeHistory> getAllTradeHistoryByUserId(@ApiParam(value = "渠道 ID", hidden = true)
                                                            @RequestAttribute("channelId") Long channelId,
                                                            @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                            @MagpieAnnotationToken MagpieToken magpieToken,
                                                            @ApiParam(value = "用户 ID", required = true, hidden = true)
                                                            @PathVariable("userId") Long userId,
                                                            @ApiParam(value = "第几页")
                                                            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                            @ApiParam(value = "页大小")
                                                            @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return fundTradeHistoryService.findAllByChannelIdAndUserId(channelId, userId, page, size);
    }
}
