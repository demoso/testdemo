package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "test_user")
public class TestUser {
    private  Long id;
    @TableField(value = "nickname")
    private  String nickName;
    private  String mobile;
    private  Long tenantId;
}
