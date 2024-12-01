package com.example.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class KioskService {
    public static void main(String[] args)
    {
        SpringApplication.run(KioskService.class, args);
    }
}
