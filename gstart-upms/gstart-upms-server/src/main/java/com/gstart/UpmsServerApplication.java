package com.gstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-03 15:23
 */

@SpringBootApplication
public class UpmsServerApplication implements BaseApplication{
    public static void main(String[] args) {
        SpringApplication.run(UpmsServerApplication.class,args);
    }
}
