package com.honeysense.magpie.spring.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeysense.magpie.spring.annotation.ip.MagpieAnnotationIpResolver;
import com.honeysense.magpie.spring.annotation.token.MagpieAnnotationTokenResolver;
import com.honeysense.magpie.spring.annotation.ua.MagpieAnnotationUaResolver;
import com.honeysense.magpie.spring.annotation.uri.MagpieAnnotationUriResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class MagpieMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
        converter.setObjectMapper(om);
        return converter;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        converters.add(stringConverter);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MagpieAnnotationIpResolver());
        resolvers.add(new MagpieAnnotationUaResolver());
        resolvers.add(new MagpieAnnotationUriResolver());
        resolvers.add(new MagpieAnnotationTokenResolver());
    }
}
