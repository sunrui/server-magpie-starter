package com.honeysense.magpie.channel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.honeysense.magpie"})
@ComponentScan(basePackages = {"com.honeysense.magpie"})
public class ChannelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChannelApplication.class, args);
    }
}
