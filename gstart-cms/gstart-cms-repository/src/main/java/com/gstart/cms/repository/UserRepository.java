package com.gstart.cms.repository;

import com.gstart.cms.rpc.api.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 14:07
 */
public interface UserRepository extends JpaRepository<User,String> {
}
