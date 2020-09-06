package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestRole {
    private  Long id;
    private  String roleName;
    private  Long tenantId;
}
