package com.gstart.upms.rpc.api;

import com.gstart.common.bean.Message;
import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 15:33
 */
@RequestMapping(value = "/api/upms")
public interface SSOService {
    @PostMapping(value = "/login")
    Message login(@RequestBody User user, @RequestParam("auth") Object auth);
}
