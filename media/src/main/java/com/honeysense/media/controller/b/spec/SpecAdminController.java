package com.honeysense.media.controller.b.spec;

import com.honeysense.media.controller.b.spec.req.PostSpecQuoteReq;
import com.honeysense.media.controller.b.spec.res.PostSpecQuoteRes;
import com.honeysense.media.entity.SpecQuote;
import com.honeysense.media.service.SpecBasicService;
import com.honeysense.media.service.SpecQuoteService;
import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
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
public class SpecAdminController {
    @Autowired
    private SpecBasicService specBasicService;
    @Autowired
    private SpecQuoteService specQuoteService;

//    @ApiOperation(value = "获取所有的基本规格", produces = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping("basic")
//    @ResponseBody
//    MagpiePage<SpecBasic> getAllSpecBasic(@ApiParam(value = "用户令牌", required = true, hidden = true)
//                                          @MagpieAnnotationToken MagpieToken magpieToken,
//                                          @ApiParam(value = "第几页")
//                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                                          @ApiParam(value = "页大小")
//                                          @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
//        return specBasicService.findAll(page, size);
//    }

    @ApiOperation(value = "创建投放规格期数", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("quote")
    @ResponseBody
    PostSpecQuoteRes postSpecQuote(@ApiParam(value = "渠道 ID", hidden = true)
                                   @RequestAttribute("channelId") Long channelId,
                                   @ApiParam(value = "用户令牌", required = true, hidden = true)
                                   @MagpieAnnotationToken MagpieToken magpieToken,
                                   @ApiParam(value = "传入参数", required = true)
                                   @Validated @RequestBody PostSpecQuoteReq req) {

        return PostSpecQuoteRes.builder().build();
    }

    @ApiOperation(value = "获取所有的投放规格期数", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("quote")
    @ResponseBody
    MagpiePage<SpecQuote> getAllSpecQuote(@ApiParam(value = "渠道 ID", hidden = true)
                                          @RequestAttribute("channelId") Long channelId,
                                          @ApiParam(value = "用户令牌", required = true, hidden = true)
                                          @MagpieAnnotationToken MagpieToken magpieToken,
                                          @ApiParam(value = "第几页")
                                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                          @ApiParam(value = "页大小")
                                          @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return specQuoteService.findAllByChannelIdDesc(channelId, page, size);
    }
}
