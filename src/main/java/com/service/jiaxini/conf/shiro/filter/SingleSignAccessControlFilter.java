package com.service.jiaxini.conf.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.service.jiaxini.conf.redis.RedisAuxiliary;
import com.service.jiaxini.conf.response.CodeMsg;
import com.service.jiaxini.conf.response.GlobalException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

/**
 * <p>
 *
 * @description: 控制单点登录过滤器
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/25
 */

@Setter
@Slf4j
public class SingleSignAccessControlFilter extends AccessControlFilter {

    /**
     * Session 管理器
     */
    private SessionManager sessionManager;

    /**
     * 加入缓存
     */
    private RedisAuxiliary cache;

    /**
     * 表示账号登录超过限制后，弹出的地址
     */
    private String kickoutUrl = "login.html";
    /**
     * 默认后者踢出前者
     */
    private boolean kickoutLeft = true;

    /**
     * 默认仅仅允许一个账号登录一次，超过了次数就踢出前面的
     */
    private int loginOnNo = 1;
    /**
     * 默认前缀
     */
    private static final String SSO_PREFIX = "single_sign_";

    /**
     * FIXME 可以发现他是调用的isAccessAllowed方法和onAccessDenied方法，只要两者有一个可以就可以了
     *
     * 逻辑是这样：先调用isAccessAllowed，如果返回的是true，则直接放行执行后面的filter和servlet;
     * 如果返回的是false，则继续执行后面的onAccessDenied方法，如果后面返回的是true则也可以有权限继续执行后面的filter和servelt。
     */

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        // 未登录，可以直接登录
        if (Objects.nonNull(subject) && !subject.isAuthenticated() && !subject.isRemembered()){
            return true;
        }

        // 已登录了，如果这个session被别人T了，会标记kickout。做任何操作都会被弹出
        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        String userName = String.valueOf(subject.getPrincipal());

        // 缓存结构：根据名字和前缀作为key ， value则是Deque
        Deque<Object> deque = (Deque<Object>)cache.get( getCurrentkey(userName));
        if (CollectionUtils.isEmpty(deque)){
            deque = new LinkedList<>();
        }

        Serializable kickoutSessionId = null;
        if (!deque.contains(sessionId)){
            deque.push(sessionId);
            while (deque.size() > loginOnNo){
                if (kickoutLeft){
                    kickoutSessionId = (Serializable)deque.removeFirst();
                }else{
                    kickoutSessionId = (Serializable)deque.removeLast();
                }
            }
        }
        cache.setWithExpire( getCurrentkey(userName), deque, 1800L);

        try{
            // 执行弹出
            if (Objects.nonNull(kickoutSessionId)){
                Session otSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (Objects.nonNull(otSession)){
                    otSession.setAttribute("kickout", true);
                }
            }
        }catch (Exception e){
            throw new GlobalException(CodeMsg.SESSION_MANAGER_ERROR);
        }

        // 不能最开始判断session的Attribute是不是有kickout属性，是因为可能自己被弹出
        if (session.getAttribute("kickout") != null){
            try{
                subject.logout();
            }catch (Exception e){   }

            saveRequest(request);
            Map<String, String> resultMap = new HashMap<String, String>();
            //判断是不是Ajax请求
            if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
                //输出json串
                out(response, resultMap);
            }else{
                //重定向
                WebUtils.issueRedirect(request, response, kickoutUrl);
            }
            return false;
        }
        return true;
    }

    private String getCurrentkey(String key){
        if ( StringUtils.isEmpty(key)){
            log.error("异常，用户登录却查不到用户信息");
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        return SSO_PREFIX + key;
    }

    private void out(ServletResponse hresponse, Map<String, String> resultMap)
            throws IOException {
        try {
            hresponse.setCharacterEncoding("UTF-8");
            PrintWriter out = hresponse.getWriter();
            out.println(JSON.toJSONString(resultMap));
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("KickoutSessionFilter.class 输出JSON异常，可以忽略。");
        }
    }
}
