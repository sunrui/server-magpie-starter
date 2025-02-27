package com.honeysense.magpie.framework.object;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.honeysense.magpie.framework.utils.convert.MagpieJsonConvert;
import com.honeysense.magpie.framework.utils.format.MagpieStringFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ApiModel(value = "全局异常")
@Slf4j
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MagpieException extends RuntimeException {
    @Getter
    public enum Type {
        /**
         * 未登录
         */
        UNAUTHORIZED,
        /**
         * 没有权限
         */
        FORBIDDEN,
        /**
         * 逻辑错误
         */
        BAD_LOGIC,
        /**
         * 主键重复
         */
        DUPLICATE,
        /**
         * 参数错误
         */
        INVALID_PARAMETER,
        /**
         * 不存在
         */
        NOT_FUND,
        /**
         * 解析错误
         */
        PARSE_FAILED,
        /**
         * 流量受到限制
         */
        RATE_LIMIT,
        /**
         * 系统正在维护
         */
        MAINTENANCE,
        /**
         * 服务器内部异常
         */
        SERVICE_UNAVAILABLE;

        public String getClassCase() {
            char[] chars = MagpieStringFormat.toCamelCase(name()).toCharArray();
            if (chars[0] >= 'a' && chars[0] <= 'z') {
                chars[0] = (char) (chars[0] - 32);
            }

            return new String(chars);
        }
    }

    @ApiModelProperty(value = "异常类型", example = "NoHandlerFound", required = true)
    private final String exception;
    @ApiModelProperty(value = "文件所在行", example = "handleNoHandlerFoundException(MagpieExceptionHandler.java:34)", required = true)
    private final String file;
    @ApiModelProperty(value = "详情信息", example = "No handler found for GET /404", required = true)
    private Object detail;

    public MagpieException(Type type) {
        this.exception = type.getClassCase();

        file = String.format("%s(%s:%s)",
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getFileName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    public MagpieException(Type type, Object detail) {
        this.exception = type.getClassCase();
        this.detail = detail;

        file = String.format("%s(%s:%s)",
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getFileName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        if (detail != null) {
            log.error(MagpieJsonConvert.toJson(this, false));
        }
    }

    public MagpieException(String exception, Object detail) {
        this.exception = exception;
        this.detail = detail;

        file = String.format("%s(%s:%s)",
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getFileName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

        if (detail != null) {
            log.error(MagpieJsonConvert.toJson(this, false));
        }
    }

    @JsonIgnore
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    public String toString() {
        return MagpieJsonConvert.toJson(this, true);
    }
}
