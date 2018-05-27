package com.gstart.upms.server.controller;


import com.gstart.common.base.BaseController;
import com.gstart.common.util.PropertyUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class ManageController extends BaseController {

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {
        System.out.println(PropertyUtil.getInstance().get("test"));
        return "";
    }

}
