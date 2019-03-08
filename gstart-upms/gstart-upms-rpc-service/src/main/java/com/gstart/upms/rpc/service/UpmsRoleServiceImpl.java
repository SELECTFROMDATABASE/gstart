package com.gstart.upms.rpc.service;

import com.gstart.upms.repository.MenuRepository;
import com.gstart.upms.repository.PositionRepository;
import com.gstart.upms.repository.RoleRepository;
import com.gstart.upms.repository.UserRepository;
import com.gstart.upms.rpc.api.UpmsRoleService;
import com.gstart.upms.rpc.api.pojo.Position;
import com.gstart.upms.rpc.api.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2019-03-07 22:21
 */
@RestController
public class UpmsRoleServiceImpl implements UpmsRoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @ResponseBody
    public List<Role> findByUserid(String userId) {
        Position p = userRepository.findById(userId).orElseThrow(RuntimeException::new).getPosition();
        return roleRepository.findPositionRoles(p.getMainId());
    }
}
