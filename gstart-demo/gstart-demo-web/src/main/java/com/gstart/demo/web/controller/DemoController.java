package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import com.gstart.demo.rpc.api.DemoService;
import com.gstart.demo.web.DemoServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {
    @Autowired
    private DemoServiceTest remoteService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {

        return remoteService.testDao();
        //return "error.jsp";
    }

}
