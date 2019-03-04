package com.gstart.cms.repository;



import com.gstart.cms.rpc.api.pojo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-03 15:46
 */
public interface MenuRepository extends JpaRepository<Menu,String> {
    @Query(value = "from com.gstart.cms.dao.pojo.Menu where parentId  = 0")
    List<Menu> getMainMenu();
}
