package com.gstart.cms.console.controller.manage;

import com.gstart.cms.console.mock.MenuServiceMock;
import com.gstart.cms.rpc.api.pojo.Menu;
import com.gstart.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-06 22:31
 */

@RestController
@CrossOrigin(origins = {"http://localhost:8080"},allowCredentials = "true")
@RequestMapping(value = "/api/manage")
public class CmsMenuController extends BaseController {

    @Autowired
    private MenuServiceMock menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles(value = {"2"})
    public List<Menu> getMainMenu() {

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession().getId());
        System.out.println("test");
        return menuService.listAll();
    }


}
