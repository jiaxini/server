package com.service.jiaxini.conf.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>
 * @description: shiro 使用 JWT 无状态认证的Token类，替换掉UsernamePasswordToken
 * 当前类中的token，将principal 和 credentials 都替换成了token
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/30
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
