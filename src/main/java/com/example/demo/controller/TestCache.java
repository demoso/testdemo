package com.example.demo.controller;

import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestUserMapper;
import com.hq.cloud.jcache.anno.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class TestCache {
    @Autowired
    private TestUserMapper testUserMapper;

    @RequestMapping("/hello")
    @ResponseBody
    @Cache(name="abcdCache", key="#id", expire = 100)
    public String hello( int id) {
        try {
            System.out.println("ddd");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getId());
        }
        return  "welcome";

    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(){
        TestUser testUsers=  testUserMapper.selectById(1L);
        testUsers.setCreateTime(new Date());
        testUsers.setUpdateTime(new Date());
        testUsers.setMobile("18908519528");
        testUserMapper.updateById(testUsers);
        //testUsers.setId();
        TestUser testUsers2=new TestUser();
        testUsers2.setMobile("18908519528");
        testUsers2.setNickName("ooo");
         testUserMapper.insert(testUsers2);
        return "ok";
    }
}
