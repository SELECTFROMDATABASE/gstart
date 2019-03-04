package com.gstart.demo.service;

import com.gstart.demo.repository.DemoRepository;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping(value = "/api/demo")
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "hello world1";
    }
    @GetMapping(value = "/dao")
    public String testDao() {
        return demoRepository.findAll().toString();
    }

    @GetMapping(value = "/insert")
    public String querytest() {
        demoRepository.ttte();
        return null;
    }

}
