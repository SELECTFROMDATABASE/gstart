package com.gstart.upms.rpc.api;

import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:35
 */
@RequestMapping(value = "/api/upms")
public interface UpmsApiService {
    @PostMapping(value = "/getbyaccount")
    User getUserByAccount(User user);
    @GetMapping(value = "/getallusers")
    List<User> getAllUser();

}
