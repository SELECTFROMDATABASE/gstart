package com.gstart.upms.client.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ Author     ：yangguangye.
 * @ Date       ：Created in 10:08 2019/2/27
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class UserToken implements AuthenticationToken {
    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getAuth(){
        return "";
    }
}
