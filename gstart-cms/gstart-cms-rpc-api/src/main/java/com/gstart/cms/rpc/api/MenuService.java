package com.gstart.cms.rpc.api;

import com.gstart.cms.rpc.api.pojo.Menu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/cms/menu")
public interface MenuService {
    @GetMapping("/getallmenu")
    List<Menu> listAll();
}
