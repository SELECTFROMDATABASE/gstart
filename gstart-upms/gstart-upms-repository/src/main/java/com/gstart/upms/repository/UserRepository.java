package com.gstart.upms.repository;


import com.gstart.upms.rpc.api.pojo.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-14 14:07
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /* public User getUserByAccount(User account) {
        return this.findOne(Example.of(account)).orElse(new User());
    }*/
}
