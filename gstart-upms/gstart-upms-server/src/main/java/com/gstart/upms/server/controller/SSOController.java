package com.gstart.upms.server.controller;

import com.gstart.common.base.BaseController;
import com.gstart.upms.rpc.api.UpmsApiService;
import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ResponseBody
    @GetMapping(value = "/getall")
    public List<User> getAll(){
        return upmsApiService.getAllUser();
    }

    /*@ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Message login(@RequestBody User u){
        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("登录成功");
        String account = u.getAccount();
        String password = u.getPassword();

        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisFactory.get(Optional.ofNullable(request.getParameter("auth")).orElse(""));
        if (StringUtils.isBlank(hasCode)){
            try {
                SecurityUtils.setSecurityManager(securityManager);
                Subject subject = SecurityUtils.getSubject();
                UserToken token = new UserToken.Server().username(u.getAccount()).password(u.getPassword()).build();
                //4、登录，即身份验证
                login(subject,token);
                //注册授权码
                String authCode = auth(subject);
                AuthMessage message1 = new AuthMessage();
                message1.setAuthCode(authCode);
                message.setData(JSONObject.fromBean(message1).toString());
            } catch (Exception e) {
                //5、身份验证失败
                if (e instanceof IncorrectCredentialsException){
                    message.setSuccess(false);
                    message.setMessage("帐号/密码错误，请重新登录");
                }else if (e instanceof LockedAccountException){
                    message.setSuccess(false);
                    message.setMessage("帐号锁定");
                }else if (e instanceof UnknownAccountException){
                    message.setSuccess(false);
                    message.setMessage("帐号不存在");
                }
                return message;
            }
        }

        //6、退出
        return message;
    }

    private void login(Subject subject, UserToken token){
        subject.login(token);
    }

    private String auth(Subject subject){
        String auth = RandomUtil.getRandomString(30, RandomUtil.TYPE.LETTER_CAPITAL_NUMBER);
        while(RedisFactory.exist(auth)){
            auth = RandomUtil.getRandomString(30, RandomUtil.TYPE.LETTER_CAPITAL_NUMBER);
        }
        RedisFactory.set(auth,(subject).toString());
        return auth;
    }*/
}
