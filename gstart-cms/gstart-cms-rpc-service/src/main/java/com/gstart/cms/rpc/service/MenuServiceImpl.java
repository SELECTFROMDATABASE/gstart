package com.gstart.cms.rpc.service;

import com.gstart.cms.dao.dao.MenuDao;
import com.gstart.cms.dao.pojo.Menu;
import com.gstart.cms.rpc.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao dao;

    @Override
    public List<Menu> listAll() {
        return dao.getMainMenu();
    }
}
