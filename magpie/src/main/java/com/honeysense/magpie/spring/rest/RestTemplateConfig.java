package com.honeysense.magpie.spring.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Log4j2
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML));

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        EchoRequestInterceptor echoRequestInterceptor = new EchoRequestInterceptor();
        interceptors.add(echoRequestInterceptor);
        restTemplate.setInterceptors(interceptors);

        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory skipSSLClientHttpRequestFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {

        return new SimpleClientHttpRequestFactory();

        //        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
//                new NoopHostnameVerifier());
//        HttpClientBuilder httpClientBuilder = HttpClients.custom();
//        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
//        CloseableHttpClient httpClient = httpClientBuilder.build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        // dealy 在国内会响应比较慢，将延迟改长一些
//        final int TIMEOUT_SECONDS = 60 * 1000;
//        factory.setReadTimeout(TIMEOUT_SECONDS);
//        factory.setConnectTimeout(TIMEOUT_SECONDS);
//        factory.setHttpClient(httpClient);
//        return factory;
    }
}
