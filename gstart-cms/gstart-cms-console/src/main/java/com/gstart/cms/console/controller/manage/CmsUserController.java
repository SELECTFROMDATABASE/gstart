package com.gstart.cms.console.controller.manage;

import com.gstart.cms.dao.pojo.User;
import com.gstart.cms.rpc.api.UserService;
import com.gstart.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.enterprise.inject.Model;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 21:29
 */

@RestController
@RequestMapping(value = "/manage/user")
public class CmsUserController extends BaseController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public String login(@RequestBody User user){
        if (user.getAccount().equals("1234")  && user.getPassword().equals("12345")){
            return "true";
        }else {
            return "false";
        }

    }
}
