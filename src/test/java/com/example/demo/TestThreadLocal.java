package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.database.datasource.UserContextHandler;
import com.example.demo.entity.TestRole;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestRoleMapper;
import com.example.demo.mapper.TestUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestThreadLocal {
    /**
     * API
     * 构造函数ThreadLocal<T>（）
     * 初始化 <T> initialValue()
     * 访问器 set/get
     * 回收 remove 当前线程的值，然后get的时候从新初始化
     */
     private static ThreadLocal<Long> x =new ThreadLocal<Long>(){
         @Override
         protected Long initialValue() {
             log.info("initialValue");
             //return 100L;
             return Thread.currentThread().getId();
         }

     };

     @Test
     public void thread1(){
         new Thread(){
             @Override
             public void run(){
                 log.info(String.valueOf(x.get()));
             }
         }.start();

        log.info(String.valueOf(x.get()));
         x.set(1002L);
         x.remove();
        log.info(String.valueOf(x.get()));


//         new Thread(new Runnable() {
//             @Override
//             public void run() {
//                 log.info(String.valueOf(x.get()));
//             }
//         }).start();
//
//
//         new Thread(() -> log.info(String.valueOf(x.get()))).start();
     }

}
