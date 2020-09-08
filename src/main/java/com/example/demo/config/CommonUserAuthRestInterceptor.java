package com.example.demo.config;

import com.example.demo.database.datasource.UserContextHandler;
//import com.jf.geeker.auth.client.annotation.IgnoreUserToken;
//import com.jf.geeker.auth.client.config.CommonUserAuthConfig;
//import com.jf.geeker.auth.client.jwt.CommonUserAuthUtil;
//import com.jf.geeker.auth.common.util.jwt.IJWTInfo;
//import com.jf.geeker.common.context.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* CommonUserAuthRestInterceptor
* @author wangdemo
* @email demoso2018@gmail.com
* @date 2018/11/15 15:43
*/
@Slf4j
public class CommonUserAuthRestInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private CommonUserAuthUtil userAuthUtil;
//
//    @Autowired
//    private CommonUserAuthConfig userAuthConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
//        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
//        if (annotation == null) {
//            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
//        }
//        if (annotation != null) {
//            return super.preHandle(request, response, handler);
//        }
        String token = request.getHeader("token");
        String name  = request.getParameter("userId");
        UserContextHandler.setTenant(token);
        UserContextHandler.setUserId(name);
//        if (StringUtils.isEmpty(token)) {
//            if (request.getCookies() != null) {
//                for (Cookie cookie : request.getCookies()) {
//                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
//                        token = cookie.getValue();
//                    }
//                }
//            }
//        }
//        IJWTInfo info = userAuthUtil.getInfoFromToken(token);
//        BaseContextHandler.setUsername(info.getUniqueName());
//        BaseContextHandler.setName(info.getName());
//        BaseContextHandler.setUserID(info.getId());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }
}
