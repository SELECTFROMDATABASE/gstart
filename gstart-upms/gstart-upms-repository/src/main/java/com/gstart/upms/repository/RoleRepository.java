package com.gstart.upms.repository;

import com.gstart.upms.rpc.api.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:30
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("from Role r where r.positions in (:positions)")
    List<Role> findPositionRoles(@Param("positions") Integer position);
}
