package com.service.jiaxini.conf.shiro.realm;

import com.service.jiaxini.po.Employee;
import com.service.jiaxini.util.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * <p>
 *
 * @description: 改在JWT认证使用的realm
 * <p>
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/30
 */
/*

public class JwtRealm extends AuthorizingRealm {

    public static final String REALMNAME = "HegemonyJWTRealm";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    */
/**
     * 授权信息
     * @param principalCollection
     * @return
     *//*

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        return null;
    }

    */
/**
     * 认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     *//*

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String)authenticationToken.getPrincipal();
        String loginName = jwtTokenUtil.getUserNameFromToken(token);
        if (StringUtils.isBlank(loginName)){
            throw new GlobalException("token invalid");
        }
        Employee employee = employeeService.selectByLoginName(loginName);
        if (Objects.isNull(employee)){
            throw new GlobalException(CodeMsg.USER_NOT_EXIT);
        }


        return null;
    }
}
*/
