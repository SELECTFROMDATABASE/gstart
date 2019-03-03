package com.gstart.upms.rpc.service;

import com.gstart.upms.repository.UserRepository;
import com.gstart.upms.rpc.api.UpmsApiService;
import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:35
 */
@Service
public class UpmsApiServiceImpl implements UpmsApiService{

    @Autowired
    private UserRepository userRepository;

    public User getUserByAccount(User user) {
        User u = userRepository.findAll(Example.of(user)).get(0);
        return u;
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
