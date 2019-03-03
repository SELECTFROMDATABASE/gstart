package com.gstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoWebApplication implements BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);

    }

}