package com.kiosk.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.KioskService.*"})
public class KioskService {

    public static void main(String[] args) {
        SpringApplication.run(KioskService.class, args);
    }
}