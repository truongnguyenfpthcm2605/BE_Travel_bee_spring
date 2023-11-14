package com.travelbee.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.travelbee.app")
@EnableScheduling
public class BeTravelbeeSpringboot3Application {
    public static void main(String[] args) {
        SpringApplication.run(BeTravelbeeSpringboot3Application.class, args);
    }
}
