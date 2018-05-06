package com.gstart.cms.console.controller;


import com.gstart.cms.dao.pojo.Menu;
import com.gstart.cms.dao.pojo.User;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/manage")
public class ManageController extends BaseController {

    @Autowired
    private MenuService menuService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public String jsp() {
        return "";
    }

}
