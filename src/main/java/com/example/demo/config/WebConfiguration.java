package com.example.demo.config;

//import com.jf.geeker.auth.client.interceptor.CommonUserAuthRestInterceptor;
//import com.jf.geeker.auth.client.interceptor.ServiceAuthRestInterceptor;
//import com.jf.geeker.auth.client.interceptor.UserAuthRestInterceptor;
//import com.jf.geeker.common.handler.GlobalExceptionHandler;
import com.example.demo.interceptor.ClientAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

/**
* WebConfiguration.java
* @author wangdemo
* @email demoso2018@gmail.com
* @date 2018/11/15 15:52
*/
@Configuration("userWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
//    @Bean
//    GlobalExceptionHandler getGlobalExceptionHandler() {
//        return new GlobalExceptionHandler();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getServiceAuthRestInterceptor())
//                .addPathPatterns("/api/**");
        registry.addInterceptor(getClientInterceptor()).
                addPathPatterns(getIncludePathPatterns());
//        registry.addInterceptor(getAdminUserAuthRestInterceptor()).addPathPatterns("/admin/**");
    }

//    @Bean
//    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
//        return new ServiceAuthRestInterceptor();
//    }

    @Bean
    ClientAuthInterceptor getClientInterceptor() {
        return new ClientAuthInterceptor();
    }
//
//    @Bean
//    UserAuthRestInterceptor getAdminUserAuthRestInterceptor(){
//         return new UserAuthRestInterceptor();
//    }

    /**
     * 需要用户和服务认证判断的路径
     *
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/**"
        };
        Collections.addAll(list, urls);
        return list;
    }

}
