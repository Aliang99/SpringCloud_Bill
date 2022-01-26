package com.aliang.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该配置类用于扫描mybatisplus的mapper接口，优化join操作，为PageHelper提供服务
 */
@Configuration
//@MapperScan("com.aliang.mapper.*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true)); // 老版本使用方式
            /*
            TODO: 新老版本的不同方式参考更新信息
                github:https://github.com/baomidou/mybatis-plus/blob/3.0/CHANGELOG.md#v331-2020117
                [v3.4.2] 2021.01.15
                feat: PaginationInnerInterceptor 添加 optimizeJoin 属性控制是否在count时对sql的join进行优化
                官网说明：
                https://baomidou.com/pages/8f40ae/
        最新版用法
            @Bean
            public MybatisPlusInterceptor mybatisPlusInterceptor() {
                MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
                interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
                return interceptor;
            }
         */

    }
}
