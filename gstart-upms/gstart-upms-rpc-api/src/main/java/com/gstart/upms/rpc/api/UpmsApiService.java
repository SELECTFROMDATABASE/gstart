package com.gstart.upms.rpc.api;

import com.gstart.upms.dao.pojo.User;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:35
 */
public interface UpmsApiService {
    User getUserByAccount(User user);
}
