package com.gstart.upms.server.controller;

import com.gstart.common.base.BaseController;
import com.gstart.common.bean.Message;
import com.gstart.common.util.RandomUtil;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.rpc.api.SSOService;
import com.gstart.upms.rpc.api.UpmsApiService;
import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 10:28
 */
@CrossOrigin(origins = {"http://localhost:8080"},allowCredentials = "true")
@RestController
@RequestMapping(value = "/sso")
public class SSOController extends BaseController {

    // 全局会话key
    private final static String GSTART_UPMS_SERVER_SESSION_ID = "gstart-upms-server-session-id";
    // 全局会话key列表
    private final static String GSTART_UPMS_SERVER_SESSION_IDS = "gstart-upms-server-session-ids";

    // code key
    private final static String GSTART_UPMS_SERVER_CODE = "gstart-upms-server-code";
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
/*
    @Autowired
    SecurityManager securityManager;*/

    @Autowired
    private  UpmsApiService upmsApiService;

    @Autowired
    private SSOService ssoService;

    @ResponseBody
    @GetMapping(value = "/getall")
    public List<User> getAll(){
        return upmsApiService.getAllUser();
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Message login(@RequestBody User u){
        return ssoService.login(u,request.getParameter("auth"));
    }


}
