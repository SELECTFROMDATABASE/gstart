package com.gstart.demo.service;

import com.gstart.demo.repository.DemoRepository;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;

    @GetMapping(value = "/instance")
    public String sayHello() {
        return "hello world1";
    }

    @GetMapping(value = "/dao")
    public String testDao() {
        return demoRepository.findAll().toString();
    }

}
