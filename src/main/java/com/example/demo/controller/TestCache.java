package com.example.demo.controller;

import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestUserMapper;
import com.hq.cloud.common.core.context.BaseContextHandler;
import com.hq.cloud.jcache.Cache;
import com.hq.cloud.jcache.CacheGetResult;
import com.hq.cloud.jcache.anno.*;
import com.hq.cloud.jcache.anno.aop.CachePointcut;
import com.hq.cloud.jcache.anno.aop.JetCacheInterceptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class TestCache {
    @Autowired
    private TestUserMapper testUserMapper;

    @CreateCache(name = "abcdCache:", expire = 160)
    private Cache<String, String> cache;
    @CreateCache(name = "tcache", expire = 160)
    private Cache<String, String> cache2;

    @RequestMapping("/hello")
    @ResponseBody
    @Cached( key="#id", expire = 160)
    public String hello( int id) {
        try {
            System.out.println("自动Refresh");

            cache2.PUT("aa","cc");
            CacheGetResult<String> cc=cache2.GET("aa");
            System.out.println("cc------>:"+ cc.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getId());
        }
        System.out.println("getTenantId------------------->"+BaseContextHandler.getTenantId());
        return BaseContextHandler.getTenantId();

    }


    @DeleteMapping("delete")
    @ResponseBody
    @CacheClear( key="#id")
    public String delete( int id) {
        try {
            System.out.println("----------------》删除完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getId());
        }
        System.out.println("getTenantId------------------->"+BaseContextHandler.getTenantId());
        return BaseContextHandler.getTenantId();
    }

    @PostMapping("update")
    @ResponseBody
    @CacheUpdate(key="#id",value = "'update9527'")
    public Long update( int id) {
        try {
            System.out.println("----------------》修改完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getId());
        }
        System.out.println("getTenantId------------------->"+BaseContextHandler.getTenantId());
        return Thread.currentThread().getId();
    }


    @RequestMapping("/update")
    @ResponseBody
    public String update(Long id){
//        TestUser testUsers=  testUserMapper.selectById(id);
//        testUsers.setMobile("18908519528");
//        testUserMapper.updateById(testUsers);

        TestUser testUsers5=new TestUser();
        testUsers5.setId(id);
        testUsers5.setMobile("18908519528");
        testUsers5.setNickName("ooo");
        testUserMapper.updateById(testUsers5);
        //testUsers.setId();
        TestUser testUsers2=new TestUser();
        testUsers2.setMobile("18908519528");
        testUsers2.setNickName("ooo");
         testUserMapper.insert(testUsers2);

        return "ok";
    }
}
