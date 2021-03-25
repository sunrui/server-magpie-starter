package com.honeysense.magpie.spring.session;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7 * 24 * 60 * 60)
public class MagpieSessionConfig {
}