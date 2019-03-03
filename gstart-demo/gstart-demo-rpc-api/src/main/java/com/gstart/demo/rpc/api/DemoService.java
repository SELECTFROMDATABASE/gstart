package com.gstart.demo.rpc.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public interface DemoService {

    String sayHello();
    String testDao();
    String querytest();
}
