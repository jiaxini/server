package com.service.jiaxini.conf.shiro.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 *
 * @description: 自定义一个SessionManager
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/23
 */

@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager {

    /**
     * 原本已有的逻辑是：如果sessionId 不为null的情况下，直接使用 sessionDAO 中取session 出来并返回
     * 修改的逻辑在它的前先从request中取看看有没有这个Session，没有的话再从sessionDAO中取，取出来存入request中
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId = this.getSessionId(sessionKey);
        ServletRequest request = null;
        // 如果是 SessionKey 是WebSessionKey的话
        if (sessionKey instanceof WebSessionKey){
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }

        if (ObjectUtils.allNotNull(request, sessionId)){
            // 从request 中尝试直接获取 session ,否则会从缓存中多次查询该session
            Object sessionDO = request.getAttribute(((String) sessionId).toLowerCase());
            if (Objects.nonNull(sessionDO)){
                return (Session)sessionDO;
            }
        }

        Session session = super.retrieveSession(sessionKey);
        if (ObjectUtils.allNotNull(request, sessionId)){
            log.info("存储session到request中");
            request.setAttribute(((String) sessionId).toLowerCase(), session);
        }
        return session;
    }
}
