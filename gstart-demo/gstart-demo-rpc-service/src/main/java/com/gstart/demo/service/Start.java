package com.gstart.demo.service;

import com.gstart.common.BaseApplication;
import com.gstart.demo.repository.DemoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)

@EnableDiscoveryClient
public class Start {
    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder(Start.class).web(WebApplicationType.SERVLET).run(args);
    }
}
