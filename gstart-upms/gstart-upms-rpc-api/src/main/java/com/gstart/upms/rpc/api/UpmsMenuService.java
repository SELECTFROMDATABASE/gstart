package com.gstart.upms.rpc.api;

import com.gstart.upms.rpc.api.pojo.Menu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:27
 */
@RequestMapping("/api/upms/menu")
public interface UpmsMenuService {
    @PostMapping("/getallmenubyrid")
    List<Menu> getAllMenuByRoleId(String roleId);
}
