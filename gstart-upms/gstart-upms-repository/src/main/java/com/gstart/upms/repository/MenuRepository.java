package com.gstart.upms.repository;


import com.gstart.upms.rpc.api.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-03 15:46
 */
public interface MenuRepository extends JpaRepository<Menu,Integer> {
    @Query(value = "from Menu where parentId  = 0")
    List<Menu> getMainMenu();
}
