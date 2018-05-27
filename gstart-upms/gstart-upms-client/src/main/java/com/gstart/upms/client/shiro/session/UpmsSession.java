package com.gstart.upms.client.shiro.session;

import org.apache.shiro.session.mgt.SimpleSession;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 16:47
 */
public class UpmsSession extends SimpleSession{
    public static enum OnlineStatus {
        /**
         * 在线
         */
        on_line("在线"),

        /**
         * 离线
         */
        off_line("离线"),

        /**
         * 强制退出
         */
        force_logout("强制退出");

        private final String info;

        private OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
    private OnlineStatus status;

    public OnlineStatus getStatus() {
        return status;
    }

    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    // 用户浏览器类型
    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
