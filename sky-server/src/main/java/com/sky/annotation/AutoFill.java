package com.sky.annotation;
/*
* 标识某个方法，对公共字段自动填充
* */

import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface AutoFill {
    //操作数据库类型
    OperationType value();
}
