package com.service.jiaxini.util;

import com.service.jiaxini.conf.redis.prefix.CookieTokenPrefix;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: cookie相关工具类
 * @author: ZengGuangfu
 */
public class CookieUtils {

    /**
     * token 存在redis中 30 分钟，在Cookie 也存储 30 分钟
     */
    public static void saveCookieValue(HttpServletResponse response, String token){
        int expire = CookieTokenPrefix.COOKIE_TOKEN.expire();
        Cookie cookie = new Cookie(CommonSentence.COOKIE_TOKEN, token);
        cookie.setMaxAge(expire);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取token中的值
     */
    public static String getCookieValue(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String result = null;
        for (Cookie cookie :cookies) {
            if(StringUtils.equals(cookie.getName(),CommonSentence.COOKIE_TOKEN)){
                result = cookie.getValue();
            }
        }
        return result;
    }

}
