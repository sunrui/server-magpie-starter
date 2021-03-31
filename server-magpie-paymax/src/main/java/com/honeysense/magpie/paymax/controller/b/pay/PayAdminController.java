package com.honeysense.magpie.paymax.controller.b.pay;

import com.honeysense.magpie.paymax.service.pay.PayOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("b/pay")
public class PayAdminController {
    @Autowired
    private PayOrderService payOrderService;

    @ApiOperation(value = "支付回调", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("paystack")
    @ResponseBody
    public void getPayStackCallback(@RequestParam("reference") String reference) {
    }
}
