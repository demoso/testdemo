package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestRole;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestRoleMapper extends BaseMapper<TestRole> {
    @Select("select b.* from test_user a,test_role b  where a.nickname=b.role_name and  b.role_name = #{name}")
    TestRole find(String name);
}
