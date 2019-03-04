package com.gstart.demo.web.mock;

import com.gstart.demo.rpc.api.DemoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 13:35 2019/3/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@FeignClient(value = "demo-service",path = "/api/demo")
public interface DemoServiceMock {
    @GetMapping(value = "/hello")
    String sayHello();

    @GetMapping(value = "/dao")
    String testDao();

    @GetMapping(value = "/insert")
    String querytest();
}
