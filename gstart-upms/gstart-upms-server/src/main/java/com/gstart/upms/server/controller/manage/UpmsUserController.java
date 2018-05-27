package com.gstart.upms.server.controller.manage;

import com.gstart.common.base.BaseController;
import com.gstart.upms.dao.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 21:29
 */

@RestController
@RequestMapping(value = "/manage/user")
public class UpmsUserController extends BaseController {

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
