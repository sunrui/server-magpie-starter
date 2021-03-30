package com.honeysense.magpie.paymax.controller.c;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.entity.account.Account;
import com.honeysense.magpie.paymax.entity.account.AccountTransaction;
import com.honeysense.magpie.paymax.service.account.AccountService;
import com.honeysense.magpie.paymax.service.account.AccountTransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("c/{appId}/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountTransactionService accountTransactionService;

    @ApiOperation(value = "获取当前账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("")
    @ResponseBody
    public Account getAccount(@ApiParam(value = "用户令牌", required = true, hidden = true)
                              @MagpieAnnotationToken MagpieToken magpieToken,
                              @PathVariable("appId") Long appId) {
        return accountService.findByAppId(appId);
    }

    @ApiOperation(value = "获取当前账户变动", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("transaction")
    @ResponseBody
    public MagpiePage<AccountTransaction> getAllAccountTransaction(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                                   @MagpieAnnotationToken MagpieToken magpieToken,
                                                                   @PathVariable("appId") Long appId,
                                                                   @ApiParam(value = "分页对象")
                                                                   @Validated MagpiePageRequest magpiePageRequest) {
        return accountTransactionService.findAllByAppId(appId, magpiePageRequest);
    }
}
