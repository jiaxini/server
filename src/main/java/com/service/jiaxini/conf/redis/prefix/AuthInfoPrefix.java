package com.service.jiaxini.conf.redis.prefix;

/**
 * <p>
 *
 * @description: 认证用户信息
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */
public class AuthInfoPrefix extends BasePrefix{

    public AuthInfoPrefix(int expireTime, String prefixStr){
        super(expireTime, prefixStr);
    }

    public AuthInfoPrefix(String prefixStr){
        super(0, prefixStr);
    }

    public static AuthInfoPrefix authSuccess = new AuthInfoPrefix(1800, "authSuccess:");

}
