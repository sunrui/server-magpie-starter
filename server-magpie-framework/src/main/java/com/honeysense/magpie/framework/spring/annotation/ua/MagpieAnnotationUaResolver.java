package com.honeysense.magpie.framework.spring.annotation.ua;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class MagpieAnnotationUaResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MagpieAnnotationUa.class) != null && parameter.getParameterType() == String.class;
    }

    @Override
    public String resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        if (userAgent.length() > 255) {
            userAgent = userAgent.substring(0, 255);
        }

        return userAgent;
    }
}
