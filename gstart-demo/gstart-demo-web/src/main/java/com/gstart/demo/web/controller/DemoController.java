package com.gstart.demo.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gstart.common.base.BaseController;
import com.gstart.demo.rpc.api.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {
    @Reference
    private DemoService ds;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {

        return ds.sayHello();
        //return "error.jsp";
    }

}
