package com.gstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UpmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpmsServiceApplication.class,args);
    }
}
