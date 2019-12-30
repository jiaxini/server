package com.service.jiaxini.conf.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * <p>
 *
 * @description: shiro数据源类
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/19
 */
/*
public class CustomRealm extends AuthorizingRealm{
    
    public static final String REALMNAME = "HegemonyRealmName";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    *//**
     * authenticationCachingEnabled 启用身份验证缓存，默认为false
     * authorizationCachingEnabled  启用授权缓存，默认为false
     *//*
    @Override
    public void setAuthenticationCachingEnabled(boolean authenticationCachingEnabled) {
        super.setAuthenticationCachingEnabled(true);
    }

    @Override
    public void setAuthorizationCachingEnabled(boolean authenticationCachingEnabled) {
        super.setAuthorizationCachingEnabled(true);
    }

    @Override
    public void setCachingEnabled(boolean cachingEnabled) {
        super.setCachingEnabled(true);
    }

    *//**
     * 认证接口，根据username获取Emp对象，封装userName 和 password 为 Subjuct做验证，并不是直接在这儿就验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *//*
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = (String)authenticationToken.getPrincipal();
        Employee employee = employeeService.selectByLoginName(loginName);
        if (Objects.isNull(employee)){
            throw new GlobalException(CodeMsg.USER_NOT_EXIT);
        }
        // 取消加盐操作，放回正确的用户名和密码即可
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginName, employee.getPassword()
                *//*, ByteSource.Util.bytes(employee.getBorn())*//*, REALMNAME);
        return simpleAuthenticationInfo;
    }

    *//**
     * 授权
     * @param principalCollection
     * @return
     *//*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取roleId
        String loginName = (String)principalCollection.getPrimaryPrincipal();
        Employee employee = employeeService.selectByLoginName(loginName);
        if (Objects.isNull(employee)){
            throw new GlobalException(CodeMsg.USER_NOT_EXIT);
        }
        Integer roleId = employee.getRoleId();

        // 获取 role 和 permission(当前仅将其放在了同一个表中)
        Role role = roleService.getById(roleId);
        String roleName = role.getRoleName();
        String permission = role.getPermission();

        // 返回 AuthorizationInfo
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(new HashSet<>(Arrays.asList(roleName)));
        simpleAuthorizationInfo.setStringPermissions(new HashSet<>(Arrays.asList(permission)));
        return simpleAuthorizationInfo;
    }



}*/
