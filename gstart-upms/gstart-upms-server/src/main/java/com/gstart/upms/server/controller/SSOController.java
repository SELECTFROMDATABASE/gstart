package com.gstart.upms.server.controller;

import com.gstart.common.base.BaseController;
import com.gstart.common.bean.Message;
import com.gstart.common.util.RandomUtil;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.client.shiro.token.UserToken;
import com.gstart.upms.rpc.api.pojo.AuthMessage;
import com.gstart.upms.rpc.api.pojo.User;
import com.gstart.upms.service.mock.UpmsUserServiceMock;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
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
    private UpmsUserServiceMock upmsUserServiceMock;


    @ResponseBody
    @GetMapping(value = "/getall")
    @RequiresRoles(value = {"developer"})
    public List<User> getAll(){
        return upmsUserServiceMock.getAllUser();
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Message login(@RequestBody User u) {

        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("登录成功");
        String account = u.getAccount();
        String password = u.getPassword();

        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisFactory.get(Optional.ofNullable(request.getParameter("auth")).orElse(""));
        if (StringUtils.isBlank(hasCode) || hasCode == null){
            try {
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
                }else{
                    e.printStackTrace();
                    message.setSuccess(false);
                    message.setMessage("内部错误");
                }
                return message;
            }
        }

        //6、退出
        return message;
    }

    @ResponseBody
    @PostMapping(value = "/logout")
    public Message logout(@RequestParam(value = "auth") String auth) {

        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("退出登录成功");
        if (RedisFactory.exist(Optional.ofNullable(auth).orElse(""))){
            try{
                RedisFactory.del(Optional.ofNullable(auth).orElse(""));
            }catch (Exception e){
                e.printStackTrace();
                message.setMessage("内部错误");
                message.setSuccess(false);
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
    }

}
