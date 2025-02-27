package com.honeysense.magpie.framework.spring.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

///**
// * 跨域过滤器
// * <p>
// * NOTE 目前并未对域名做特殊处理
// */
//@Component
//public class MagpieCorsFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With,Authorization, x-platform, Content-Type,Token,Accept, Connection, User-Agent, Cookie");
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
