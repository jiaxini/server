package com.service.jiaxini.conf.mybatis_plus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * @description: Mybatis-plus 配置文件
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */

@Configuration
public class MybatisPlusConfiguration {

    /**
     * mybatis-plus 分页插件使用
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(20);   // 限制最大分页pageSize
        return paginationInterceptor;
    }
}
