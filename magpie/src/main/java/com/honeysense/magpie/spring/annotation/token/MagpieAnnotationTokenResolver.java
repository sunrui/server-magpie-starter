package com.honeysense.magpie.spring.annotation.token;

import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.config.MagpieConfig;
import com.honeysense.magpie.entity.MagpieToken;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class MagpieAnnotationTokenResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(MagpieAnnotationToken.class) != null && parameter.getParameterType() == MagpieToken.class;
    }

    public static MagpieToken parseToken(HttpServletRequest httpServletRequest) {
        String token = null;

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().contentEquals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            throw new MagpieException(MagpieException.Type.UNAUTHORIZED);
        }

        MagpieToken magpieToken = MagpieConfig.jwt().unSign(token);

        if (new Date().after(magpieToken.getExpiredAt())) {
            throw new MagpieException(MagpieException.Type.UNAUTHORIZED);
        }

        return magpieToken;
    }

    @Override
    public MagpieToken resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                       NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        return MagpieAnnotationTokenResolver.parseToken((HttpServletRequest) nativeWebRequest.getNativeRequest());
    }
}
