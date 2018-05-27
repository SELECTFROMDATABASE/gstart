package com.gstart.upms.client.shiro.filter;

import com.gstart.common.util.PropertyUtil;
import com.gstart.upms.common.constant.UpmsConstant;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-15 23:05
 */
public class UpmsAuthenticationFilter extends AuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsAuthenticationFilter.class);

    // 局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_ID = "gstart-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String GSTART_UPMS_CLIENT_SESSION_IDS = "gstart-upms-client-session-ids";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println(this.getClass() +" ----------------- isAccessAllowed");
        Subject subject = getSubject(request,response);
        Session session = subject.getSession();
        String upmsType = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");
        session.setAttribute(UpmsConstant.UPMS_TYPE, upmsType);
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        System.out.println(this.getClass() +" ----------------- onAccessDenied");
        StringBuffer ssoServerUrl = new StringBuffer(PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.sso.server.url"));
        return false;
    }
}
