package com.gstart.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-08-08 14:57
 */
public class AuthCodeUtil {
    /**
     * 获取code
     * @param userId 用户id
     * @param appType 应用类型
     * @param expire 过期时间
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getCode(String userId,String appType,long expire) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis()+expire);
        Algorithm algorithm = Algorithm.HMAC512("akjdsvcjkabwaljhsdvce");
        // 附带username信息
        return JWT.create()
                .withExpiresAt(date)
                .withClaim("userId",userId)
                .withClaim("appType",appType)
                .sign(algorithm);
    }

    /**
     * 获取用户ID
     * @param token
     * @return
     */
    public String getUserId(String token){
        return JWT.decode(token).getClaim("userId").asString();
    }

    public boolean verify(String token,String appType) {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC512("akjdsvcjkabwaljhsdvce");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("appType", appType)
                    .build();
            if(getUserId(token)==null){
                return false;
            }
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(RandomStringUtils.randomAlphabetic(60));;
            System.out.println(new org.apache.oltu.oauth2.as.issuer.UUIDValueGenerator().generateValue());
            System.out.println(new MD5Generator().generateValue());;
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
    }
}
