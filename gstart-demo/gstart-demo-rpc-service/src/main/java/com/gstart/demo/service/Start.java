package com.gstart.demo.service;

import com.gstart.common.BaseStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Start extends BaseStart {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Start.class, args);
    }
}
