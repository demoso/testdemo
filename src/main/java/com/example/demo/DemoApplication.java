package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.example.demo.authrize.annotation.EnablePreAuthorize;
import com.hq.cloud.jcache.anno.config.EnableCreateCacheAnnotation;
import com.hq.cloud.jcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class,
                                  SecurityAutoConfiguration.class,
                                  ManagementWebSecurityAutoConfiguration.class})

@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.example.demo"})
@MapperScan(basePackages = "com.example.demo.mapper")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnablePreAuthorize
public class DemoApplication {
//    org.aspectj.weaver.tools.PointcutPrimitive
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
