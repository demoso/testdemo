package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface TestUserMapper extends BaseMapper<TestUser> {
    @Select("select * from test_user a,test_role b  where a.nickname=b.role_name and  a.nickname = #{name}")
    TestUser find(String name);

    //@Select("select * from test_user a,test_role b  where a.id=b.id and  a.nickname = #{name}")
    TestUser mySelect(String name);
}
