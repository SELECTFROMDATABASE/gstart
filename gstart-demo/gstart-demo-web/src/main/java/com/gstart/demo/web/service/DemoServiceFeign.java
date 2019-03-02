package com.gstart.demo.web.service;

import com.gstart.demo.rpc.api.DemoService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 16:38 2019/2/28
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@FeignClient(value = "demo-service")
public interface DemoServiceFeign extends DemoService {

}
