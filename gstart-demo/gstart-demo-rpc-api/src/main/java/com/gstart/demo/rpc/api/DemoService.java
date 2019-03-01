package com.gstart.demo.rpc.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/demo")
public interface DemoService {

    @GetMapping(value = "/instance")
    String sayHello();
    @GetMapping(value = "/dao")
    String testDao();
}
