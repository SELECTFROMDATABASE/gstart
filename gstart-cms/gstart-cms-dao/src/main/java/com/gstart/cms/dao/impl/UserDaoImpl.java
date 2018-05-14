package com.gstart.cms.dao.impl;

import com.gstart.cms.dao.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 14:07
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

}
