package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "test_user")
public class TestUser {
    private  Long id;
    @TableField(value = "nickname")
    private  String nickName;
    private  String mobile;
    private  Long tenantId;
    @Version
    private Integer version;
    private Date    updateTime;
    private Date   createTime;
}
