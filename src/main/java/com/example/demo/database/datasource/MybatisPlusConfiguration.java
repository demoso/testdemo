package com.example.demo.database.datasource;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.LongValue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jimmy
 * @date 2020-01-08
 */
@Slf4j
@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisPlusConfiguration {

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //行级别多租户
        TenantLineInnerInterceptor tenantLineInnerInterceptor= new TenantLineInnerInterceptor();
        tenantLineInnerInterceptor.setTenantLineHandler(() -> new LongValue(1L));
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        //动态schema
        DynamicSchemaInterceptor dynamicSchemaInterceptor=new DynamicSchemaInterceptor();
        dynamicSchemaInterceptor.setTenantDatabasePrefix("hq");
        dynamicSchemaInterceptor.setBaseTenantHandler(new BaseTenantHandler() {
            @Override
            public String getTenantId() {
                return UserContextHandler.getTenant();
            }
        });
        interceptor.addInnerInterceptor(dynamicSchemaInterceptor);
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

//    @Bean
//    public ConfigurationCustomizer configurationCustomizer() {
//        return configuration -> configuration.setUseDeprecatedExecutor(false);
//    }


}