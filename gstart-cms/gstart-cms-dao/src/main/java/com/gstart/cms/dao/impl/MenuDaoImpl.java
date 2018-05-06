package com.gstart.cms.dao.impl;

import com.gstart.cms.dao.dao.MenuDao;
import com.gstart.cms.dao.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-03 15:45
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Override
    public void save(Menu menu) {
        hibernateTemplate.save(menu);
    }

    public List<Menu> getAll(){
        return hibernateTemplate.loadAll(Menu.class);
    }
    public List<Menu> getMainMenu(){
        return (List<Menu>) hibernateTemplate.find("from com.gstart.cms.dao.pojo.Menu where parentId  = ?","0");
    }
}
