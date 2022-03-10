package com.xxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.xxs")
@EnableScheduling
public class SpringBootDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
    
}
