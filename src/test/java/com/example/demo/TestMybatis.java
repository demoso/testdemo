package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.database.datasource.UserContextHandler;
import com.example.demo.entity.TestRole;
import com.example.demo.entity.TestUser;
import com.example.demo.mapper.TestRoleMapper;
import com.example.demo.mapper.TestUserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
     @Autowired
     private TestUserMapper testUserMapper;
    @Autowired
    private TestRoleMapper testRoleMapper;

     @Test
     public void select(){
        TestUser  testUsers=  testUserMapper.selectById(1L);
        log.info("+++++++>"+JSON.toJSONString(testUsers));
         // testUsers.forEach(testUser -> System.out.println(JSON.toJSONString(testUser)));

         TestUser  user=  testUserMapper.find("于远");
         log.info("+++++++>"+JSON.toJSONString(user));
         // testUsers.forEach(testUser -> System.out.println(JSON.toJSONString(testUser)));

         TestUser  user2=  testUserMapper.find("于远");
         log.info("+++++++>"+JSON.toJSONString(user2));
         // testUsers.forEach(testUser -> System.out.println(JSON.toJSONString(testUser)));

         TestRole testRole=testRoleMapper.selectById(7L);
         log.info("+++++++>"+JSON.toJSONString(testRole));

         TestRole testRole3=testRoleMapper.find("于远");
         log.info("+++++++>"+JSON.toJSONString(testRole3));
     }

     @Test
    public void update(){
         UserContextHandler.setTenant("user");
         TestUser  testUsers=  testUserMapper.selectById(1L);
         testUsers.setMobile("18908519528");
         testUserMapper.updateById(testUsers);
         testUsers.setId(testUsers.getId()+5);
         testUserMapper.insert(testUsers);
     }

    @Test
    public void delete(){
        UserContextHandler.setTenant("user");
         testUserMapper.deleteById(6L);
    }
}
