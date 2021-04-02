package com.honeysense.magpie.paymax.controller.a.app;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.paymax.entity.app.App;
import com.honeysense.magpie.paymax.service.app.AppService;
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
@RequestMapping("a/app")
public class AppAdminController {
    @Autowired
    private AppService appService;

    @ApiOperation(value = "获取所有的 App", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseBody
    public MagpiePage<App> getAllApp(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                     @MagpieAnnotationToken MagpieToken magpieToken,
                                     @ApiParam(value = "分页对象")
                                     MagpiePageRequest magpiePageRequest) {
        return appService.findAll(magpiePageRequest);
    }
}
