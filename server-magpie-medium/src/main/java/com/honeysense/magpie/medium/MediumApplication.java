package com.honeysense.magpie.medium;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.honeysense.magpie"})
public class MediumApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediumApplication.class, args);
    }
}
