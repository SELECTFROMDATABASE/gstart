package com.gstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.log4j2.SpringBootConfigurationFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 13:20 2019/3/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DemoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class,args);

    }
}
