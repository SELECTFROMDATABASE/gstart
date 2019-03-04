package com.gstart.cms.rpc.service;

import com.gstart.cms.repository.MenuRepository;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.cms.rpc.api.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> listAll() {
        return menuRepository.getMainMenu();
    }
}
