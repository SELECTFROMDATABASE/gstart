package com.gstart.upms.client.shiro.realm;

import com.gstart.common.util.PropertyUtil;
import com.gstart.upms.client.shiro.token.UserToken;
import com.gstart.upms.rpc.api.pojo.Menu;
import com.gstart.upms.rpc.api.pojo.Role;
import com.gstart.upms.rpc.api.pojo.User;
import com.gstart.upms.service.mock.UpmsMenuServiceMock;
import com.gstart.upms.service.mock.UpmsRoleServiceMock;
import com.gstart.upms.service.mock.UpmsUserServiceMock;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-19 15:12
 */

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UpmsUserServiceMock upmsUserServiceMock;
    @Autowired
    private UpmsRoleServiceMock upmsRoleServiceMock;
    @Autowired
    private UpmsMenuServiceMock upmsMenuServiceMock;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String type = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");
        switch (type) {
            case "client": {
                return (AuthorizationInfo) client("doGetAuthorizationInfo", principalCollection);
            }
            case "server": {
                return (AuthorizationInfo) server("doGetAuthorizationInfo", principalCollection);
            }
            default: {
                return (AuthorizationInfo) server("doGetAuthorizationInfo", principalCollection);
            }
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String type = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");
        switch (type) {
            case "client": {
                return (AuthenticationInfo) client("doGetAuthenticationInfo", authenticationToken);
            }
            case "server": {
                return (AuthenticationInfo) server("doGetAuthenticationInfo", authenticationToken);
            }
            default: {
                return (AuthenticationInfo) server("doGetAuthenticationInfo", authenticationToken);
            }
        }
    }

    private Object server(String method, Object... o) {
        switch (method) {
            case "doGetAuthorizationInfo": {
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

                System.out.println("---------------- 执行 Shiro 权限获取 ---------------------");
                Object principal = ((PrincipalCollection)o[0]).getPrimaryPrincipal();
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                if (principal instanceof User) {
                    User userLogin = (User) principal;
                    if (userLogin != null) {
                        List<Role> roleList = upmsRoleServiceMock.findByUserid(userLogin.getMainId());
                        if (CollectionUtils.isNotEmpty(roleList)) {
                            for (Role role : roleList) {
                                info.addRole(role.getRoleName());

                                List<Menu> menuList = upmsMenuServiceMock.getAllMenuByRoleId(role.getMainId());
                                if (CollectionUtils.isNotEmpty(menuList)) {
                                    for (Menu menu : menuList) {
                                        //menuLabel 权限标识
                                        if (StringUtils.isNotBlank(menu.getMenuLabel())) {
                                            info.addStringPermission(menu.getMenuLabel());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("---------------- 获取到以下权限 ----------------");
                System.out.println(info.getStringPermissions().toString());
                System.out.println("---------------- Shiro 权限获取成功 ----------------------");

                System.out.println(this.getClass() + " server ----------------- doGetAuthorizationInfo");
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
                return simpleAuthorizationInfo;
            }
            case "doGetAuthenticationInfo": {
                System.out.println(this.getClass() + " server ----------------- doGetAuthenticationInfo");
                AuthenticationToken token = ((AuthenticationToken) o[0]);
                String username = token.getPrincipal().toString();
                String password = token.getCredentials().toString();
                User u = new User();
                u.setAccount(username);
                String upmsType = PropertyUtil.getInstance("gstart-upms-client").get("gstart.upms.type");
        /*if ("client".equals(upmsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }*/

                // 查询用户信息
                User upmsUser = upmsUserServiceMock.getUserByAccount(u);
                System.out.println(upmsUser);
                if (null == upmsUser) {
                    throw new UnknownAccountException();
                }
                if (!upmsUser.getPassword().equals(password)) {
                    throw new IncorrectCredentialsException();
                }
                if (Optional.ofNullable(upmsUser.getStatus()).orElse("").equals("3")) {
                    throw new LockedAccountException();
                }
                return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
            }
            default: {
                return null;
            }
        }

    }

    private Object client(String method, Object... o) {
        switch (method) {
            case "doGetAuthorizationInfo": {
                System.out.println(this.getClass() + " client ----------------- doGetAuthorizationInfo");
                SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
                return simpleAuthorizationInfo;
            }
            case "doGetAuthenticationInfo": {
                System.out.println(this.getClass() + " client ----------------- doGetAuthenticationInfo");
                AuthenticationToken token = (AuthenticationToken) o[0];

                UserToken oAuth2Token = (UserToken) token;
                String auth = oAuth2Token.getAuth(); //获取 auth code
                String username = (auth); // 提取用户名
                SimpleAuthenticationInfo authenticationInfo =
                        new SimpleAuthenticationInfo(username, auth, getName());
                return authenticationInfo;
            }
            default: {

            }
        }
        return null;
    }
}
