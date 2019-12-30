package com.service.jiaxini.conf.shiro;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 *
 * @description: shiro部分配置
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/26
 */

@Data
@SpringBootConfiguration
@PropertySource(value = "classpath:shiro.properties")
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    private String annoUrl;

    private String authcUrl;

    private String logoutUrl;

    private Long sessionTimeout = 1800L;

    private String kickoutFilterKey = "kickout";

    private CustomProperty custom = new CustomProperty();

}
