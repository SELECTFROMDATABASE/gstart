package com.gstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 15:35 2019/3/7
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class,args);
    }
}
