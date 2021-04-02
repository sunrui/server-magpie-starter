package com.honeysense.magpie.paymax.controller.a.account;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.entity.account.Account;
import com.honeysense.magpie.paymax.service.account.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("a/account")
public class AccountAdminController {
    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "获取所有的账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseBody
    public MagpiePage<Account> getAllAccount(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                             @MagpieAnnotationToken MagpieToken magpieToken,
                                             @ApiParam(value = "分页对象")
                                             MagpiePageRequest magpiePageRequest) {
        return accountService.findAll(magpiePageRequest);
    }
}
