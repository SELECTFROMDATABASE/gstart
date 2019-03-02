package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import com.gstart.demo.web.service.DemoServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {
    @Autowired
    private DemoServiceFeign demoServiceFeign;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {

        return demoServiceFeign.testDao()+" hello world;";
        //return "error.jsp";
    }

}
