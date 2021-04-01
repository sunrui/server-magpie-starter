package com.honeysense.magpie.medium.controller.b.spec;

import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.medium.controller.b.spec.req.PostMediumSpecQuoteReq;
import com.honeysense.magpie.medium.controller.b.spec.res.PostMediumSpecQuoteRes;
import com.honeysense.magpie.medium.entity.MediumSpecQuote;
import com.honeysense.magpie.medium.service.MediumSpecBasicService;
import com.honeysense.magpie.medium.service.MediumSpecQuoteService;
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

@Api(tags = "后台 - 规格")
@RestController
@RequestMapping("b/{channel}/spec")
public class SpecMediumAdminController {
    @Autowired
    private MediumSpecBasicService mediumSpecBasicService;
    @Autowired
    private MediumSpecQuoteService mediumSpecQuoteService;

//    @ApiOperation(value = "获取所有的基本规格", produces = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping("basic")
//    @ResponseBody
//    MagpiePage<MediumSpecBasic> getAllSpecBasic(@ApiParam(value = "用户令牌", required = true, hidden = true)
//                                          @MagpieAnnotationToken MagpieToken magpieToken,
//                                          @ApiParam(value = "第几页")
//                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                                          @ApiParam(value = "页大小")
//                                          @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
//        return mediumSpecBasicService.findAll(page, size);
//    }

    @ApiOperation(value = "创建投放规格期数", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("quote")
    @ResponseBody
    PostMediumSpecQuoteRes postSpecQuote(@ApiParam(value = "开发者 ID", hidden = true)
                                         @RequestAttribute("appId") Long appId,
                                         @ApiParam(value = "用户令牌", required = true, hidden = true)
                                         @MagpieAnnotationToken MagpieToken magpieToken,
                                         @ApiParam(value = "传入参数", required = true)
                                         @Validated @RequestBody PostMediumSpecQuoteReq req) {

        return PostMediumSpecQuoteRes.builder().build();
    }

    @ApiOperation(value = "获取所有的投放规格期数", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("quote")
    @ResponseBody
    MagpiePage<MediumSpecQuote> getAllSpecQuote(@ApiParam(value = "开发者 ID", hidden = true)
                                                @RequestAttribute("appId") Long appId,
                                                @ApiParam(value = "用户令牌", required = true, hidden = true)
                                                @MagpieAnnotationToken MagpieToken magpieToken,
                                                @ApiParam(value = "分页对象")
                                                @Validated MagpiePageRequest magpiePageRequest) {
        return mediumSpecQuoteService.findAllByAppIdDesc(appId, magpiePageRequest);
    }
}
