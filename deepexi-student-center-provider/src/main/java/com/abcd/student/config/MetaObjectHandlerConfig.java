package com.abcd.student.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    // mybatis-plus公共字段自动填充，https://baomidou.oschina.io/mybatis-plus-doc/#/auto-fill
    @Override
    public void insertFill(MetaObject metaObject) {
//        System.out.println("插入方法实体填充");
    	setFieldValByName("createdTime", new Date(), metaObject);
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("createdBy", "", metaObject);
        setFieldValByName("updatedBy", "", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        System.out.println("更新方法实体填充");
    	setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("updatedBy", "", metaObject);
    }
}