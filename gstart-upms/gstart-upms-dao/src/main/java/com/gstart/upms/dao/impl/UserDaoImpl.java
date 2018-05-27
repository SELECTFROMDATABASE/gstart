package com.gstart.upms.dao.impl;

import com.gstart.upms.dao.dao.UserDao;
import com.gstart.upms.dao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 14:07
 */
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    @Override
    public User getUserByAccount(User account) {
        List<User> list = (List<User>) hibernateTemplate.findByExample(account);
        if (list.isEmpty()){
            return null;
        }else {
            return list.get(0);
        }
    }
}
