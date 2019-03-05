package com.gstart.upms.client.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-08-06 15:54
 */
public class UserToken implements AuthenticationToken {

    private String token;
    private String auth;
    private String userName;
    private String password;

    public static class Client{
        private  String auth;

        public Client auth(String auth){
            this.auth = auth;
            return this;
        }
        public UserToken build(){
            return new UserToken(auth);
        }
    }
    public static class Server{
        private String token;
        private String auth;
        private String userName;
        private String password;

        public Server token(String token){
            this.token = token;
            return this;
        }
        public Server auth(String auth){
            this.auth = auth;
            return this;
        }

        public Server username(String userName){
            this.userName = userName;
            return this;
        }

        public Server password(String password){
            this.password = password;
            return this;
        }

        public UserToken build(){
            return new UserToken(token,auth,userName,password);
        }
    }

    public UserToken(String token, String auth, String userName, String password) {
        this.token = token;
        this.auth = auth;
        this.userName = userName;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String getAuth() {
        return auth;
    }

    public UserToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.userName;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }
}
