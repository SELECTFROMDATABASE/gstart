package com.gstart.upms.rpc.api;

import com.gstart.upms.rpc.api.pojo.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 21:29
 */
@RequestMapping(value = "/api/upms/role")
public interface UpmsRoleService {
    @PostMapping(value = "/findbyuid")
    List<Role> findByUserid(String userId);
}
