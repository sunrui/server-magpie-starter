package com.honeysense.magpie.paymax.controller.a.app;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.controller.a.app.req.PostAppConfigPayStackReq;
import com.honeysense.magpie.paymax.controller.a.app.res.DeleteAppConfigPayStackRes;
import com.honeysense.magpie.paymax.controller.a.app.res.PostAppConfigPayStackRes;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.entity.app.AppConfigPayStack;
import com.honeysense.magpie.paymax.service.app.AppService;
import com.honeysense.magpie.paymax.service.gateway.AppConfigPayStackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("a/app")
public class AppAdminController {
    @Autowired
    private AppService appService;
    @Autowired
    private AppConfigPayStackService appConfigPayStackService;

    @GetMapping("config/paystack")
    @ResponseBody
    public MagpiePage<AppConfigPayStack> getAppConfigPayStack(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                                              @Validated @RequestBody PostAppConfigPayStackReq req) {
        return appConfigPayStackService.findAll(MagpiePageRequest.builder().page(0).pageSize(99).build());
    }

    @PostMapping("config/paystack")
    @ResponseBody
    public PostAppConfigPayStackRes postAppConfigPayStack(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                                          @Validated @RequestBody PostAppConfigPayStackReq req) {
        AppConfigPayStack appConfigPayStack = appConfigPayStackService.findByName(req.getName());
        if (appConfigPayStack != null) {
            return PostAppConfigPayStackRes.builder().nameExists(true).build();
        }

        appConfigPayStack = new AppConfigPayStack();
        appConfigPayStack.setName(req.getName());
        appConfigPayStack.setPublicKey(req.getPublicKey());
        appConfigPayStack.setSecretKey(req.getSecretKey());
        appConfigPayStack.setReference(0);
        appConfigPayStack.setEmail(req.getEmail());
        appConfigPayStack.setPassword(req.getPassword());
        appConfigPayStack.setComment(req.getComment());

        appConfigPayStack = appConfigPayStackService.save(appConfigPayStack);

        return PostAppConfigPayStackRes.builder().appConfigPayStackId(appConfigPayStack.getId()).build();
    }

    @ApiOperation(value = "发起支付", produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping("config/paystack/{appConfigPayStackId}")
    @ResponseBody
    public DeleteAppConfigPayStackRes deleteAppConfigPayStack(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                                              @MagpieAnnotationToken MagpieToken magpieToken,
                                                              @PathVariable("appConfigPayStackId") Long appConfigPayStackId) {
        AppConfigPayStack appConfigPayStack = appConfigPayStackService.findById(appConfigPayStackId);
        if (appConfigPayStack == null) {
            return DeleteAppConfigPayStackRes.builder().appConfigPayStackIdNotExists(true).build();
        }

        appConfigPayStackService.deleteById(appConfigPayStackId);
        return DeleteAppConfigPayStackRes.builder().success(true).build();
    }

    @ApiOperation(value = "获取所有的 App", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseBody
    public MagpiePage<App> getAllApp(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                     @MagpieAnnotationToken MagpieToken magpieToken,
                                     @ApiParam(value = "分页对象") MagpiePageRequest magpiePageRequest) {
        return appService.findAll(magpiePageRequest);
    }
}
