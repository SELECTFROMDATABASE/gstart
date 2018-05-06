package com.gstart.cms.dao.dao;


import com.gstart.cms.dao.pojo.Menu;

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
