//package com.honeysense.magpie.spring.filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MagpieFilterConfiguration {
//    @Bean
//    public FilterRegistrationBean<MagpieEchoFilter> echoFilterRegistration() {
//        FilterRegistrationBean<MagpieEchoFilter> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new MagpieEchoFilter());
//        registration.addUrlPatterns("/*");
//        registration.setName("EchoFilter");
//        registration.setOrder(0);
//        return registration;
//    }
//}
