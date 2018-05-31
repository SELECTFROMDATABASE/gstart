package com.gstart.upms.server.controller;

import com.gstart.common.base.BaseController;
import com.gstart.common.bean.Message;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.client.shiro.session.UpmsSessionDao;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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
    SecurityManager securityManager;


    @Autowired
    UpmsSessionDao upmsSessionDao;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Message login(@RequestBody com.gstart.upms.dao.pojo.User u){
        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("登录成功");
        String account = u.getAccount();
        String password = u.getPassword();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        UsernamePasswordToken token = new UsernamePasswordToken(u.getAccount(), u.getPassword());

        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisFactory.get(GSTART_UPMS_SERVER_SESSION_ID + "_" + sessionId);
        System.out.println(hasCode == null);
        if (StringUtils.isBlank(hasCode)){
            try {
                //4、登录，即身份验证
                subject.login(token);
                // 全局会话sessionId列表，供会话管理
                RedisFactory.lpush(GSTART_UPMS_SERVER_SESSION_IDS, sessionId.toString());
                // 默认验证帐号密码正确，创建code
                String code = UUID.randomUUID().toString();
                // 全局会话的code
                RedisFactory.set(GSTART_UPMS_SERVER_SESSION_ID + "_" + sessionId, code, (int) subject.getSession().getTimeout() / 1000);
                // code校验值
                //RedisFactory.set(GSTART_UPMS_SERVER_CODE + "_" + code, code, (int) subject.getSession().getTimeout() / 1000);
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
}
