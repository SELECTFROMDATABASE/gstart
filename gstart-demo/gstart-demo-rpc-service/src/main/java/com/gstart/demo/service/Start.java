package com.gstart.demo.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:*.xml");
        context.start();
        System.in.read();
    }
}
