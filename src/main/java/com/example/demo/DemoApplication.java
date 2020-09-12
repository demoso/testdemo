package com.example.demo;

import com.example.demo.beanfactory.BenzCar;
import com.example.demo.beanfactory.Engine;
import com.example.demo.beanfactory.SpecialBeanForEngine;
import com.hq.cloud.jcache.anno.config.EnableCreateCacheAnnotation;
import com.hq.cloud.jcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.example.demo"})
//@MapperScan(basePackages = "com.example.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
