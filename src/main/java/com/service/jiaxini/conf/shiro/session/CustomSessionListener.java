package com.service.jiaxini.conf.shiro.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * <p>
 *
 * @description: session监听器
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/25
 */
@Slf4j
public class CustomSessionListener implements SessionListener{
    @Override
    public void onStart(Session session) {
        log.info("shiro Session 监听开启，此时sessionID = {}" , session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.info("shiro Session 会话停止，此时sessionID = {}" , session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("会话中断，中断了shiro SessionDAO监听，此时sessionID: {}" , session.getId());
    }
}
