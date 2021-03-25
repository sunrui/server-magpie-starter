package com.honeysense.user.controller.b;

import com.honeysense.magpie.entity.MagpiePage;
import com.honeysense.magpie.entity.MagpieToken;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationToken;
import com.honeysense.user.entity.User;
import com.honeysense.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = "后台 - 用户")
@RestController
@RequestMapping("b/user")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取所有的用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    @ResponseBody
    MagpiePage<User> getAllUser(@ApiParam(value = "用户令牌", required = true, hidden = true)
                                @MagpieAnnotationToken MagpieToken magpieToken,
                                @ApiParam(value = "第几页")
                                @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                @ApiParam(value = "页大小")
                                @RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
        return userService.findAll(page, size);
    }
}
