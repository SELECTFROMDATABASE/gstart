package com.gstart.cms.console.controller;


import com.gstart.cms.rpc.api.MenuService;
import com.gstart.common.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/manage")
@CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3600,allowCredentials = "true")
public class ManageController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    @RequiresPermissions(value = {"2"})
    public String jsp() {
        return "testManager";
    }

}
