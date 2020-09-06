package com.example.demo.controller;

import com.hq.cloud.jcache.anno.Cache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestCache {

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
}
