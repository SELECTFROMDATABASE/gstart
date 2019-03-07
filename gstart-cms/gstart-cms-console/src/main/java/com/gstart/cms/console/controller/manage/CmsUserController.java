package com.gstart.cms.console.controller.manage;

import com.gstart.cms.console.mock.UserServiceMock;
import com.gstart.cms.rpc.api.pojo.User;
import com.gstart.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 21:29
 */

@RestController
@RequestMapping(value = "/manage")
public class CmsUserController extends BaseController {

    @Autowired
    private UserServiceMock userService;

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
