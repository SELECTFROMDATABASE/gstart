package com.gstart.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.gstart.demo.dao.dao.DemoDao;
import com.gstart.demo.dao.pojo.Demo;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DemoServiceImpl implements DemoService{

    @Autowired
    private DemoDao demoDao;
    @Override
    public String sayHello() {
        return "hello world";
    }

    @Override
    public void createDemo(Demo demo) {/*
        demoDao.saveDemo(demo);*/
    }
}
