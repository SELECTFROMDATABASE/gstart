package com.gstart.upms.dao.dao;

import com.gstart.upms.dao.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 14:07
 */
public interface UserDao {
    User getUserByAccount(User account);
}
