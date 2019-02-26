package com.gstart.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Start {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Start.class, args);
    }
}
