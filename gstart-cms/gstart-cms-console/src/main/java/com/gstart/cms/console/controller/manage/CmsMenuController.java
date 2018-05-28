package com.gstart.cms.console.controller.manage;

import com.gstart.cms.dao.pojo.Menu;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-06 22:31
 */

@RestController
@RequestMapping(value = "/manage")
public class CmsMenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3600,allowCredentials = "true")
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getMainMenu() {
        return menuService.listAll();
    }
}
