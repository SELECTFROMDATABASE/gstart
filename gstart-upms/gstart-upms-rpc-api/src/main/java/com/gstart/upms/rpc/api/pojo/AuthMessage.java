package com.gstart.upms.rpc.api.pojo;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-10-31 22:55
 */
public class AuthMessage {
    private String authCode;
    private long maxTime;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }
}
