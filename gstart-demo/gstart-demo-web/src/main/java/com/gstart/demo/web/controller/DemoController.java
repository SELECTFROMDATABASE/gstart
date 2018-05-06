package com.gstart.demo.web.controller;

import com.gstart.common.base.BaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class DemoController extends BaseController {
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {
        return "error.jsp";
    }

}
