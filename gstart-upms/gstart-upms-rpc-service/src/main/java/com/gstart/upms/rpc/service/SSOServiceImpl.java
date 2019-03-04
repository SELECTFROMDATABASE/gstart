package com.gstart.upms.rpc.service;

import com.gstart.common.bean.Message;
import com.gstart.common.util.RandomUtil;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.client.shiro.token.UserToken;
import com.gstart.upms.rpc.api.SSOService;
import com.gstart.upms.rpc.api.pojo.User;
import com.gstart.upms.rpc.service.bean.AuthMessage;
import junit.framework.Assert;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 15:32
 */
@Service
public class SSOServiceImpl implements SSOService{

    @Autowired
    SecurityManager securityManager;

    public static void main(String[] args) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
        new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到SecurityManager实例 并绑定给
        org.apache.shiro.mgt.SecurityManager securityManager =  factory.getInstance();
        SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        Subject subject1 = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        UsernamePasswordToken token1 = new UsernamePasswordToken("wang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        subject.getSession().setAttribute("test","123");
        System.out.println(subject.getSession().getAttribute("test"));;
        //6、退出

        subject1.login(token1);
        subject1.getSession().setAttribute("test","123");
        System.out.println(subject.getSession().getAttribute("test"));;
        subject.logout();
        subject1.logout();
    }

    @Override
    public Message login(User u,Object auth) {

        Message message = new Message();
        message.setSuccess(true);
        message.setMessage("登录成功");
        String account = u.getAccount();
        String password = u.getPassword();

        // 判断是否已登录，如果已登录，则回跳，防止重复登录
        String hasCode = RedisFactory.get(Optional.ofNullable(auth.toString()).orElse(""));
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
    }
}
