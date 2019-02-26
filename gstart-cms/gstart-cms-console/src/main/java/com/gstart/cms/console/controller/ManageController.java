package com.gstart.cms.console.controller;


import com.gstart.cms.dao.pojo.Menu;
import com.gstart.cms.dao.pojo.User;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.common.base.BaseController;
import com.gstart.common.util.RedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/manage")
@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3600,allowCredentials = "true")
public class ManageController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {
        return "";
    }

}
