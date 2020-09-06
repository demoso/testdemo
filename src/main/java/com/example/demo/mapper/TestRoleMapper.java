package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.TestRole;
import com.example.demo.entity.TestUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestRoleMapper extends BaseMapper<TestRole> {
    //自写的sql请按规范书写(sql涉及到多个表的每个表都要给别名,特别是 inner join 的要写标准的 inner join)
    @Select("select b.* from test_user a left join test_role b  on  a.nickname=b.role_name join test_role c on a.nickname=c.role_name where  b.role_name = #{name}")
    TestRole find(String name);
}
