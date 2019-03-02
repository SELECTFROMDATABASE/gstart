package com.gstart.demo.service;

import com.gstart.demo.repository.DemoRepository;
import com.gstart.demo.rpc.api.DemoService;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoServiceImpl implements DemoService{
/*
    @Autowired
    private DemoRepository demoRepository;*/

    public String sayHello() {
        return "hello world1";
    }

    public String testDao() {
        return "1111";
    }

}
