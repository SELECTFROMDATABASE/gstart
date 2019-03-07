package com.gstart.cms.rpc.service;

import com.gstart.cms.repository.MenuRepository;
import com.gstart.cms.rpc.api.MenuService;
import com.gstart.cms.rpc.api.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listAll() {
        return menuRepository.getMainMenu();
    }
}
