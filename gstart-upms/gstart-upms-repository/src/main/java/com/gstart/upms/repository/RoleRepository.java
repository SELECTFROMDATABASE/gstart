package com.gstart.upms.repository;

import com.gstart.upms.rpc.api.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:30
 */
public interface RoleRepository extends JpaRepository<Role,String> {
}
