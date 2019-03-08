package com.gstart.upms.rpc.service;

import com.gstart.upms.repository.MenuRepository;
import com.gstart.upms.repository.RoleRepository;
import com.gstart.upms.rpc.api.UpmsMenuService;
import com.gstart.upms.rpc.api.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 22:22
 */
@RestController
public class UpmsMenuServiceImpl implements UpmsMenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RoleRepository roleRepository;

    @ResponseBody
    public List<Menu> getAllMenuByRoleId(String roleId) {
        return roleRepository.findById(Integer.valueOf(roleId)).orElseThrow(RuntimeException::new).getMenus();
    }
}
