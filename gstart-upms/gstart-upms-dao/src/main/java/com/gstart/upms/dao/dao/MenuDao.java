package com.gstart.upms.dao.dao;


import com.gstart.upms.dao.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-03 15:46
 */
public interface MenuDao {
    void save(Menu menu);
    List<Menu> getAll();
    List<Menu> getMainMenu();
}
