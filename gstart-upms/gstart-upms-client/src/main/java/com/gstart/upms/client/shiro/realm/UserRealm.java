package com.gstart.upms.client.shiro.realm;

import com.gstart.common.util.PropertyUtil;
import com.gstart.upms.dao.pojo.User;
import com.gstart.upms.rpc.api.UpmsApiService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:12
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UpmsApiService upmsApiService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
       /* User upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        // 当前用户所有角色
        List<UpmsRole> upmsRoles = upmsApiService.selectUpmsRoleByUpmsUserId(upmsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UpmsRole upmsRole : upmsRoles) {
            if (StringUtils.isNotBlank(upmsRole.getName())) {
                roles.add(upmsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UpmsPermission> upmsPermissions = upmsApiService.selectUpmsPermissionByUpmsUserId(upmsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UpmsPermission upmsPermission : upmsPermissions) {
            if (StringUtils.isNotBlank(upmsPermission.getPermissionValue())) {
                permissions.add(upmsPermission.getPermissionValue());
            }
        }*/
        System.out.println(this.getClass() +" ----------------- doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(this.getClass() +" ----------------- doGetAuthenticationInfo");

        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        User u = new User();
        u.setAccount(username);
        // client无密认证
        String upmsType = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");
        /*if ("client".equals(upmsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }*/

        // 查询用户信息
        User upmsUser = upmsApiService.getUserByAccount(u);
        if (null == upmsUser) {
            throw new UnknownAccountException();
        }
        if (!upmsUser.getPassword().equals(password)){
            throw new IncorrectCredentialsException();
        }
        if (upmsUser.getStatus().equals("3")) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
