package com.travelbee.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.travelbee.app")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync(proxyTargetClass = true)
@EnableCaching
public class BeTravelbeeSpringboot3Application {
    public static void main(String[] args) {
        SpringApplication.run(BeTravelbeeSpringboot3Application.class, args);
    }
}
