package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.hq.cloud.jcache.anno.config.EnableCreateCacheAnnotation;
import com.hq.cloud.jcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.example.demo"})
//@MapperScan(basePackages = "com.example.demo.mapper")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
