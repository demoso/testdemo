package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.TestUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        TestUser testUsers5=new TestUser();
        testUsers5.setNickName("于远");
        TestUser testUsers6=new TestUser();
        BeanUtils.copyProperties(testUsers5,testUsers6);
        System.out.println(JSON.toJSONString(testUsers5));
        System.out.println(JSON.toJSONString(testUsers6));
        if (testUsers6==testUsers5){
            System.out.println("true");
        }
    }

}
