package com.example.demo.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xjface on 2017/9/12.
 */

public class ClientAuthInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ClientAuthInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // HandlerMethod handlerMethod = (HandlerMethod) handler;
        logger.info("request.getRequestURI:"+request.getRequestURI());
        logger.info("request.getRemoteHost:"+request.getRemoteHost());
        logger.info("request.getRequestURL:"+request.getRequestURL());
		return super.preHandle(request, response, handler);
    }
}
