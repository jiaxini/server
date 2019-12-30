package com.service.jiaxini.conf.shiro.session;

import com.service.jiaxini.conf.redis.RedisAuxiliary;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 *
 * @description: 自定义实现RedisSessionDAO 配置
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/23
 */

@Slf4j
public class CustomRedisSessionDao extends AbstractSessionDAO {
    
    public static final String SHIRO_SESSION_PREFIX = "authentication_session_";

    @Autowired
    private RedisAuxiliary redisAuxiliary;

    /**
     * XXX 工具方法，保存Session到redis中, 新增方法和更新方法用
     */
    public void saveSession(Session session){
        log.info("储存session: sessionId = {}", session.getId());
        if (ObjectUtils.allNotNull(session, session.getId())){
            String currentyKey = getCurrentyKey(session.getId().toString());
            // redisAuxiliary 缓存的时间单位是s，而不是ms
            redisAuxiliary.setWithExpire(currentyKey, session, session.getTimeout()/1000);
        }
    }

    /**
     * XXX 工具方法，给Key 加前缀
     */
    protected String getCurrentyKey(String key){
        return SHIRO_SESSION_PREFIX + key;
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.info("创建session会话");
        // 获取SessionID
        Serializable sessionId = super.generateSessionId(session);
        // 绑定SessionId 到Session
        assignSessionId(session, sessionId.toString());

        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (Objects.isNull(sessionId)){
            return null;
        }
        Session session = (Session)redisAuxiliary.get(getCurrentyKey(sessionId.toString()));
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if (!ObjectUtils.anyNotNull(session, session.getId())){
            return;
        }
        saveSession(session);

        /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if(!request.getServletPath().startsWith("/static/") && !request.getServletPath().startsWith("/webjars")) {
            System.out.print("更新session会话信息"+session.getId()+"----"+request.getServletPath());
            saveSession(session);
        }*/
    }

    @Override
    public void delete(Session session) {
        if (ObjectUtils.allNotNull(session, session.getId())){
            redisAuxiliary.delete(getCurrentyKey(session.getId().toString()));
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<String> keySet = redisAuxiliary.keys(SHIRO_SESSION_PREFIX);
        if (CollectionUtils.isEmpty(keySet)){
            return null;
        }
        Collection<Session> sessionCollection = new HashSet<>();
        keySet.forEach( key -> {
            Session session = (Session)redisAuxiliary.get(getCurrentyKey(key));
            sessionCollection.add(session);
        });
        return sessionCollection;
    }
}
