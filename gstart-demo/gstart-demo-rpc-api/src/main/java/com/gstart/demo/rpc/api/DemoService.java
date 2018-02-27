package com.gstart.demo.rpc.api;

import com.gstart.demo.dao.pojo.Demo;

public interface DemoService {
    String sayHello();
    void createDemo(Demo demo);
}
