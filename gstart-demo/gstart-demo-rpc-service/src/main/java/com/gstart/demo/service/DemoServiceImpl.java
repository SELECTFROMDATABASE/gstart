package com.gstart.demo.service;

import com.gstart.demo.repository.DemoRepository;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoRepository demoRepository;

    public String sayHello() {
        return "hello world1";
    }

    public String testDao() {
        return demoRepository.findAll().toString();
    }

    public String querytest() {
        demoRepository.ttte();
        return null;
    }

}
