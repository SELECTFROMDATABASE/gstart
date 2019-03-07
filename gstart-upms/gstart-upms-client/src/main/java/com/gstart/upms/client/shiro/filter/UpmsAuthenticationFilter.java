package com.gstart.upms.client.shiro.filter;

import com.gstart.common.util.PropertyUtil;
import com.gstart.common.util.RedisFactory;
import com.gstart.upms.client.shiro.exception.UserNoAuthException;
import com.gstart.upms.client.shiro.token.UserToken;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 23:05
 */
public class UpmsAuthenticationFilter extends BasicHttpAuthenticationFilter {
    private String type = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    // 局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_ID = "gstart-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_IDS = "gstart-upms-client-session-ids";

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("------------ loginAttempt");
        return super.isLoginAttempt(request, response);
    }


    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //若客户端，创建UserToken
        System.out.println("------------ createToken");

        String auth = request.getParameter("auth");
        if (type.equals("client"))
            return new UserToken.Client().auth(auth).build();
        else
            return super.createToken(request,response);
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("------------ preHandle");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            //判断是否给过
            httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        System.out.println("------------ isAccessAllowed");
        if (type.equals("client")){
            //检查code有效性
            return RedisFactory.exist(Optional.ofNullable(((HttpServletRequest)request).getHeader("Authorization")).orElseThrow(UserNoAuthException::new));
        }else{
            return super.isAccessAllowed(request,response,mappedValue);
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println(this.getClass() +" ----------------- onAccessDenied");
        StringBuffer ssoServerUrl = new StringBuffer(PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.sso.server.url"));
        return false;
    }



}
