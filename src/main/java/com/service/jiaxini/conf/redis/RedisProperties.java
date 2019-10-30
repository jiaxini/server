package com.service.jiaxini.conf.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * @description: Redis配置信息
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;
    private int port;
    private int database;
    private String password;
}
