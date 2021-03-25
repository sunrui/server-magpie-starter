package com.honeysense.sms;

import com.honeysense.magpie.config.MagpieConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.honeysense"})
public class SmsApplication implements CommandLineRunner {
    @Value("${snowFlake.workerId}")
    private Long snowFlakeWorkerId;
    @Value("${snowFlake.datacenterId}")
    private Long snowFlakeDatacenterId;
    @Value("${jwtToken.secret}")
    private String jwtTokenSecret;

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        MagpieConfig.snowFlakeWorkerId = snowFlakeWorkerId;
        MagpieConfig.snowFlakeDataCenterId = snowFlakeDatacenterId;
        MagpieConfig.jwtTokenSecret = jwtTokenSecret;
    }
}
