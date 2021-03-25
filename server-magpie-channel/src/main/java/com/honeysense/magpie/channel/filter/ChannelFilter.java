//package com.honeysense.channel.filter;
//
//import com.honeysense.channel.entity.Channel;
//import com.honeysense.channel.service.ChannelService;
//import com.honeysense.magpie.entity.MagpieException;
//import com.honeysense.magpie.entity.MagpieToken;
//import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationTokenResolver;
//import com.honeysense.user.entity.User;
//import com.honeysense.user.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class ChannelFilter implements Filter {
//    @Autowired
//    private ChannelService channelService;
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//
//        if (true) {
//            return;
//        }
//
//        String uri = httpServletRequest.getRequestURI();
//        String[] split = uri.split("/");
//        if (split.length < 3) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//
//        if (split[1].contentEquals("media")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//            return;
//        }
//
//        Channel channel;
//
//        try {
//            channel = channelService.findByShortId(split[2]);
//            if (channel == null) {
//                Map<String, String> map = new HashMap<>();
//                map.put("shortId", split[2]);
//
//                throw new MagpieException(MagpieException.Type.NOT_FUND, map);
//            }
//        } catch (MagpieException e) {
//            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//            httpServletResponse.getWriter().print(e.toString());
//            return;
//        }
//
//        httpServletRequest.setAttribute("channelId", channel.getId());
//
//        if (uri.startsWith("/a")) {
//            MagpieToken magpieToken = MagpieAnnotationTokenResolver.parseToken(httpServletRequest);
//            User user = userService.findById(magpieToken.getUserId());
//
//            String[] phones = new String[]{
//                    "15068860507"
//            };
//
//            boolean whiteRole = false;
//            for (String phone : phones) {
//                if (phone.contentEquals(user.getPhone())) {
//                    whiteRole = true;
//                    break;
//                }
//            }
//
//            if (!whiteRole) {
//                MagpieException magpieException = new MagpieException(MagpieException.Type.FORBIDDEN);
//                httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//                httpServletResponse.getWriter().print(magpieException.toString());
//                return;
//            }
//
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
