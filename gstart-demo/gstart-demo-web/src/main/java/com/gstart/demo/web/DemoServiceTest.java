package com.gstart.demo.web;

import com.gstart.demo.rpc.api.DemoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 16:38 2019/2/28
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@FeignClient(value = "demo-service")
public interface DemoServiceTest extends DemoService {

}
