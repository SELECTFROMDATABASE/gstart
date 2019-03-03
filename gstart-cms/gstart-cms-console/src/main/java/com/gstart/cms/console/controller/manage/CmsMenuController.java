package com.gstart.cms.console.controller.manage;

import com.gstart.cms.dao.pojo.Menu;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.common.base.BaseController;
import com.gstart.upms.repository.pojo.User;
import org.apache.shiro.SecurityUtils;
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
    private MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getMainMenu() {

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession().getId());
        System.out.println("test");
        return menuService.listAll();
    }


}
