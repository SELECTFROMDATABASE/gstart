package com.gstart.upms.client.shiro.exception;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-10-31 22:20
 */
public class UserNoAuthException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Current User No Auth.Please Login!";
    }
}
