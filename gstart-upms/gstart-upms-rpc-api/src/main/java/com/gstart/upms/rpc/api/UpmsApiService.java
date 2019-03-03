package com.gstart.upms.rpc.api;

import com.gstart.upms.rpc.api.pojo.User;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:35
 */
public interface UpmsApiService {
    User getUserByAccount(User user);
    List<User> getAllUser();

}
