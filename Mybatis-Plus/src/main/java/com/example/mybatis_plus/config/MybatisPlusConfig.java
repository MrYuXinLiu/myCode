package com.example.mybatis_plus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *@Author: lxy
 * @Date: 2019/2/22 18:08
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.mybatis_plus.mapper")
public class MybatisPlusConfig {

    /**
    * 乐观锁插件,一定记得在实体类加入一个@Version注解, 另外如果想设定版本,
    * 那么在实体类加入@TableField(fill = FieldFill.INSERT) 另外还有别忘了配置MetaObjectHandle
    */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     *  逻辑删除插件 ,首先你需要在实体类上加入两个注解
     *  @TableLogic
     *  @TableField(fill = FieldFill.INSERT)
     *  另外记得要加入到MetaObjectHandle实现这个接口的类中
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL 执行性能分析插件
     * 开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长
     * 使用之前请把使用环境设置到开发或者测试状态 spring.profiles.active=dev
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);//ms，超过此处设置的ms则sql不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

}
