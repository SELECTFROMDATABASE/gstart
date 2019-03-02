package com.gstart;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class DemoServiceApplication implements BaseApplication {
    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(DemoServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}
