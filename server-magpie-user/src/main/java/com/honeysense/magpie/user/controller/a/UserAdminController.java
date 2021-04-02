package com.honeysense.magpie.user.controller.a;

import com.honeysense.magpie.framework.object.MagpiePage;
import com.honeysense.magpie.framework.object.MagpiePageRequest;
import com.honeysense.magpie.framework.object.MagpieToken;
import com.honeysense.magpie.framework.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.magpie.user.entity.User;
import com.honeysense.magpie.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 用户")
@RestController
@RequestMapping("a/user")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取所有的用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseBody
    MagpiePage<User> getAllUser(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                @MagpieAnnotationToken MagpieToken magpieToken,
                                @ApiParam(value = "分页对象")
                                MagpiePageRequest magpiePageRequest) {
        return userService.findAll(magpiePageRequest);
    }
}
