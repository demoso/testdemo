package com.example.demo.database;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.example.demo.database.datasource.UserContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
public class EntityMetaObjectHandler implements MetaObjectHandler {
    /**
     * 获取当前交易的用户，为空返回默认system
     *
     * @return
     */

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "createBy", String.class, UserContextHandler.getUserId().toString());
        this.strictInsertFill(metaObject, "updateBy", String.class, UserContextHandler.getUserId().toString());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
//        this.strictUpdateFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
//        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        //setFieldValByName("createTime", LocalDateTime.now(),  metaObject);



    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....>>"+UserContextHandler.getUserId().toString());
        this.strictUpdateFill(metaObject, "updateBy", String.class,UserContextHandler.getUserId().toString());
       // this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)


    }
}