package com.gstart.upms.client.shiro;

import com.gstart.common.util.PropertyUtil;
import com.gstart.common.util.RandomUtil;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.client.shiro.filter.UpmsAuthenticationFilter;
import com.gstart.upms.client.shiro.realm.UserRealm;
import com.gstart.upms.client.shiro.session.UpmsSessionManager;
import com.gstart.upms.client.shiro.subject.UpmsSubjectFactory;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.io.Serializable;
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

    public static String SESSIONIDPREFIX;
    public static String AUTHORIZATION = "Authorization";
    public static String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    @Value("${gstart.upms.session.id}")
    public static void setSESSIONIDPREFIX(String SESSIONIDPREFIX) {
        ShiroConfig.SESSIONIDPREFIX = SESSIONIDPREFIX;
    }

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
        //自定义Rememberme 管理
        securityManager.setRememberMeManager(rememberMeManager());
/*

        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator storageEvaluator =
                (DefaultSessionStorageEvaluator)subjectDAO.getSessionStorageEvaluator();
        UpmsSubjectFactory subjectFactory = new UpmsSubjectFactory(storageEvaluator);
        securityManager.setSubjectFactory(subjectFactory);
*/

        return securityManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */

    @Bean
    public RedisCacheManager cacheManager() {

        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setKeyPrefix("SPRINGBOOT_CACHE:");   //设置前缀
        return redisCacheManager;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(CodecSupport.toBytes("safwaefasdf2134++"));
        rememberMeManager.setCookie(this.getRememberMeCookie());
        return rememberMeManager;
    }

    @Bean
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMeCookie");
        simpleCookie.setHttpOnly(Boolean.TRUE);
        return simpleCookie;

    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setSessionIdGenerator(session -> {
            String auth = RandomUtil.getRandomString(30, RandomUtil.TYPE.LETTER_CAPITAL_NUMBER);
            while(RedisFactory.exist(auth)){
                auth = RandomUtil.getRandomString(30, RandomUtil.TYPE.LETTER_CAPITAL_NUMBER);
            }
            return auth;
        });
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix(SESSIONIDPREFIX);
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public SessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie(AUTHORIZATION);
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);

        UpmsSessionManager sessionManager = new UpmsSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());/*
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);*/
        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(PropertyUtil.getInstance("redis").get("master.redis.ip"));
        redisManager.setPort(PropertyUtil.getInstance("redis").getInt("master.redis.port"));
        //redisManager.setTimeout(1800); //设置过期时间
        redisManager.setPassword(PropertyUtil.getInstance("redis").get("master.redis.password"));
        return redisManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
