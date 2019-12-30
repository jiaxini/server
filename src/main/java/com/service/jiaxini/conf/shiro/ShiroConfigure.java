package com.service.jiaxini.conf.shiro;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.service.jiaxini.conf.redis.RedisAuxiliary;
import com.service.jiaxini.conf.redis.RedisProperties;
import com.service.jiaxini.conf.shiro.cache.CustomCacheManager;
import com.service.jiaxini.conf.shiro.filter.SingleSignAccessControlFilter;
//import com.service.jiaxini.conf.shiro.realm.CustomRealm;
import com.service.jiaxini.conf.shiro.session.CustomRedisSessionDao;
import com.service.jiaxini.conf.shiro.session.CustomSessionListener;
import com.service.jiaxini.conf.shiro.session.CustomSessionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * @description: Shiro 重要配置信息
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/19
 */

@Configuration
public class ShiroConfigure {

    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private ShiroProperties shiroProperties;

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        securityManager.setAuthenticator(authenticator());
        //securityManager.setRealm(realm());  // realm放在了authenticator中
        return securityManager;
    }

    @Bean
    public Realm realm(){
        /*CustomRealm customRealm = new CustomRealm();
        customRealm.setCachingEnabled(true);
        customRealm.setAuthenticationCachingEnabled(true);
        customRealm.setAuthorizationCachingEnabled(true);
        return customRealm;*/
        return new AuthorizingRealm() {
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                return null;
            }

            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
                return null;
            }
        };
    }

    // @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put(shiroProperties.getKickoutFilterKey(), accessControlFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 拦截器，顺序相关
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        // 添加链接
        addUrl(param, shiroProperties.getAnnoUrl(), "anon");
        addUrl(param, shiroProperties.getLogoutUrl(), "logout");
        addUrl(param, shiroProperties.getAuthcUrl(), "authc");
        // 添加过滤器到登录链接
        addUrl(param,"/login", shiroProperties.getKickoutFilterKey());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(param);

        // 登录页面、成功跳转页和未授权页面
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getCustom().getLoginUrl());
        shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getCustom().getMainUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getCustom().getUnauthorizedUrl());
        return shiroFilterFactoryBean;
    }

    @Bean
    public RememberMeManager rememberMeManager(){
        return new CookieRememberMeManager();
    }

    @Bean
    public CacheManager cacheManager(){
        return new CustomCacheManager();
    }

    /**
     * shiro开启注解
     */

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启aop注解支持
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 使用系统自带的SessionManager   RedisSessionDAO  和  RedisManager
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = SessionManager.class)
    /*@Qualifier(value = "sysSessionManager")*/
    public SessionManager defaultWebSessionManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPort(redisProperties.getPort());
        redisManager.setPassword(redisProperties.getPassword());
        redisManager.setTimeout(1800);

        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * ==================================== Session 相关设置=====================================
     */
    @Bean
    @Primary
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(sessionDAO());
        customSessionManager.setSessionListeners(Arrays.asList(sessionListener()));
        return customSessionManager;
    }

    @Bean
    protected SessionDAO sessionDAO(){
        CustomRedisSessionDao customRedisSessionDao = new CustomRedisSessionDao();
        return customRedisSessionDao;
    }

    @Bean
    protected SessionListener sessionListener(){
        return new CustomSessionListener();
    }


    /**
     * =========================================================================================
     * =================================配置验证器策略(多个Realm的情况下)===========================
     * FirstSuccessfulStrategy 只要有一个成功即可，返回第一个成功的信息
     * AtLeatOneSuccessfulStrategy 只要有一个成功即可,返回所有成功了的信息  （默认）
     * AllSuccessfulStrategy  所有Realm验证成功才算成功，且返回所有Realm身份认证成功的认证信息
     */
    @Bean
    protected Authenticator authenticator(){
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        FirstSuccessfulStrategy strategy = new FirstSuccessfulStrategy();
        HashSet<Realm> realmSet = new HashSet<Realm>();
        realmSet.add(realm());
        // 同时设置相关的realm 和 strategy
        authenticator.setRealms(realmSet);
        authenticator.setAuthenticationStrategy(strategy);
        return authenticator;
    }

    /**
     * 此bean是为了解决shiro过滤器中setUnauthorizedUrl("")设置的路径不起作用,xml配置的话就不需要这个
     */
    @Bean
    public HandlerExceptionResolver solver() {
        HandlerExceptionResolver handlerExceptionResolver = new MyExceptionResover();
        return handlerExceptionResolver;
    }

    @Autowired
    private RedisAuxiliary redisAuxiliary;

    /**
     * ControlFilter，单点登录使用Redis管理
     */
    @Bean
    public AccessControlFilter accessControlFilter(){
        SingleSignAccessControlFilter singleSignAccessControlFilter = new SingleSignAccessControlFilter();
        singleSignAccessControlFilter.setCache(redisAuxiliary);
        singleSignAccessControlFilter.setSessionManager(sessionManager());
        return singleSignAccessControlFilter;
    }

    /**
     * shiro方言  支持shiro标签
     * @return
     * @link https://blog.csdn.net/zhuzhezhuzhe1/article/details/80583950
     */
    /*@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }*/

    private void addUrl(Map param, String urlArrayOperation, String power){
        String[] urlArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlArrayOperation, StringPool.COMMA);
        for (String url : urlArray) {
            if (!StringUtils.isAllEmpty(url, power))
            param.put(url, power);
        }
    }

}
