package com.gstart.upms.rpc.service;

import com.gstart.upms.rpc.api.SSOService;
import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 15:32
 */
public class SSOServiceImpl implements SSOService{
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
}
