package com.gstart.upms.rpc.service;

import com.gstart.upms.dao.dao.UserDao;
import com.gstart.upms.dao.pojo.User;
import com.gstart.upms.rpc.api.UpmsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:35
 */
public class UpmsApiServiceImpl implements UpmsApiService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByAccount(User user) {
        User u = userDao.getUserByAccount(user);
        return u;
    }
}
