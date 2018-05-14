package com.gstart.cms.rpc.service;

import com.gstart.cms.dao.dao.UserDao;
import com.gstart.cms.rpc.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 21:27
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

}
