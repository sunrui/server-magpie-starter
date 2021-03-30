package com.honeysense.magpie.paymax.controller.c.pay;

import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.controller.c.pay.req.PostPayOrderForPayStackReq;
import com.honeysense.magpie.paymax.controller.c.pay.res.PostPayOrderForPayStackRes;
import com.honeysense.magpie.paymax.http.paystack.PayStackProvider;
import com.honeysense.magpie.paymax.http.paystack.initialize.PostPayStackInitializeReq;
import com.honeysense.magpie.paymax.service.pay.PayOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("c/{appId}/pay")
public class PayOrderController {
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private PayStackProvider payStackProvider;

    @ApiOperation(value = "获取当前账户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("paystack")
    @ResponseBody
    public PostPayOrderForPayStackRes postPayForPayStack(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                         @MagpieAnnotationToken MagpieToken magpieToken,
                                                         @PathVariable("appId") Long appId,
                                                         @Validated @RequestBody PostPayOrderForPayStackReq req) {
        PostPayStackInitializeReq postPayStackInitializeReq = new PostPayStackInitializeReq();
        postPayStackInitializeReq.setEmail(req.getEmail());
        postPayStackInitializeReq.setAmount(req.getAmount());
//
//        payStackProvider.postInitialize()

        return null;
    }
}
