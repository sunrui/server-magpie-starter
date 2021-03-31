package com.honeysense.magpie.paymax.controller.c.pay;

import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.controller.c.pay.req.PostPayOrderForPayStackReq;
import com.honeysense.magpie.paymax.controller.c.pay.res.PostPayOrderForPayStackRes;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.entity.pay.PayOrder;
import com.honeysense.magpie.paymax.http.paystack.PayStackProvider;
import com.honeysense.magpie.paymax.http.paystack.PayStackRes;
import com.honeysense.magpie.paymax.http.paystack.initialize.PostPayStackInitializeReq;
import com.honeysense.magpie.paymax.http.paystack.initialize.PostPayStackInitializeRes;
import com.honeysense.magpie.paymax.service.app.AppService;
import com.honeysense.magpie.paymax.service.pay.PayOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("c/{appId}/pay")
public class PayOrderController {
    @Autowired
    private AppService appService;
    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private PayStackProvider payStackProvider;

    @ApiOperation(value = "发起支付", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("paystack")
    @ResponseBody
    public PostPayOrderForPayStackRes postPayForPayStack(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                         @MagpieAnnotationToken MagpieToken magpieToken,
                                                         @PathVariable("appId") Long appId,
                                                         @Validated @RequestBody PostPayOrderForPayStackReq req) {
        App app = appService.findById(appId);
        if (app == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("appId", appId);

            throw new MagpieException(MagpieException.Type.NOT_FUND, map);
        }

        PostPayStackInitializeReq postPayStackInitializeReq = new PostPayStackInitializeReq();
        postPayStackInitializeReq.setEmail(req.getEmail());
        postPayStackInitializeReq.setAmount(req.getAmount());

        PayStackRes<PostPayStackInitializeRes> payStackRes = payStackProvider.postInitialize(app.getSecret(), postPayStackInitializeReq);

        PayOrder payOrder = payOrderService.insert(appId, req.getAppUserId(), payStackRes.getData().getReference(),
                req.getPayload(), req.getAmount(), -1);

        return PostPayOrderForPayStackRes.builder()
                .payOrderId(payOrder.getId())
                .payUrl(payStackRes.getData().getAuthorization_url())
                .build();
    }
}
