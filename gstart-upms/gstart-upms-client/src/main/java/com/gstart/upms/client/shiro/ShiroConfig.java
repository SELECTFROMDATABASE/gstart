package com.gstart.upms.client.shiro;

import com.gstart.common.util.PropertyUtil;
import com.gstart.upms.client.shiro.filter.UpmsAuthenticationFilter;
import com.gstart.upms.client.shiro.realm.UserRealm;
import com.gstart.upms.client.shiro.session.UpmsSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 10:23 2019/3/4
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
@Configuration
public class ShiroConfig {
    @Value("${gstart.upms.sso.server.url}")
    private String loginUrl;
    @Value("${gstart.upms.unauthorizedUrl}")
    private String unauthorizedUrl;
    @Value("${gstart.upms.successUrl}")
    private String successUrl;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/api/upms/login", "anon");
        filterChainDefinitionMap.put("/api/manage/**", "authc");
        filterChainDefinitionMap.put("/api/manage/index", "authc");

        filterChainDefinitionMap.put("/swagger-ui.html", "user");
        filterChainDefinitionMap.put("/sso/login", "anon");
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "user");
        filterChainDefinitionMap.put("/api/**", "authc");
        filterChainDefinitionMap.put("/**", "anon");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        Map m = new HashMap<String, Filter>();
        m.put("authc", new UpmsAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(m);
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public UserRealm myShiroRealm() {
        UserRealm myShiroRealm = new UserRealm();
        return myShiroRealm;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());

        return securityManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix("SPRINGBOOT_CACHE:");   //设置前缀
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("SPRINGBOOT_SESSION:");
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public SessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie("Authorization");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        UpmsSessionManager sessionManager = new UpmsSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(PropertyUtil.getInstance("redis").get("master.redis.ip"));
        redisManager.setPort(PropertyUtil.getInstance("redis").getInt("master.redis.port"));
        //redisManager.setTimeout(1800); //设置过期时间
        redisManager.setPassword(PropertyUtil.getInstance("redis").get("master.redis.password"));
        return redisManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
}
