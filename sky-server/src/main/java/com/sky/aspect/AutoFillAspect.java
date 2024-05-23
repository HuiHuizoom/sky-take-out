package com.sky.aspect;


import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Slf4j
@Component
@Aspect
public class AutoFillAspect {
    //切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    //通知
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始填充");

        //获取数据库操作参数
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operation = autoFill.value();


        //获取方法参数，操作对象
        Object[] args = joinPoint.getArgs();

        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];

        //准备数据
        LocalDateTime time = LocalDateTime.now();
        Long id = BaseContext.getCurrentId();

        //进行赋值
        if(operation == OperationType.INSERT){
            try {

                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setCreateUser.invoke(entity,id);
                setUpdateUser.invoke(entity,id);
                setCreateTime.invoke(entity,time);
                setUpdateTime.invoke(entity,time);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        else if(operation == OperationType.UPDATE){

            try {
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);

                setUpdateUser.invoke(entity,id);
                setUpdateTime.invoke(entity,time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




}
