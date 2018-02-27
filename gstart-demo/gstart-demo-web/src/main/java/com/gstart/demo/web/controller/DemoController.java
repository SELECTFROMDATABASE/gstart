package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import com.gstart.demo.dao.pojo.Demo;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/a" ,method = RequestMethod.GET)
    public String jsp(){
        return demoService.sayHello();
    }

    @RequestMapping(value = "/form" , method = RequestMethod.POST)
    public Demo saveDemo(@ModelAttribute Demo demo){
        demoService.createDemo(demo);
        return demo;
    }

}
