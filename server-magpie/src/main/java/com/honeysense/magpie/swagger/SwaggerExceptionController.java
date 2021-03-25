package com.honeysense.magpie.swagger;

import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@Api(tags = "异常注册")
@RestController
public class SwaggerExceptionController {
    @Data
    public static class MagpieException {
        @ApiModelProperty(value = "异常类型", example = "NoHandlerFound", required = true)
        private String exception;
        @ApiModelProperty(value = "文件所在行", example = "handleNoHandlerFoundException(MagpieExceptionHandler.java:34)",required = true)
        private String file;
        @ApiModelProperty(value = "详情信息", example = "No handler found for GET /hello",required = true)
        private Object detail;
    }

    @ApiOperation(value = "注册出错对象到 Swagger", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "223B3143-946B-4D16-A3FB-E5495B33D1AC")
    @ApiResponses({@ApiResponse(code = 400, message = "ERROR", response = MagpieException.class)})
    public void initException() {
    }
}
