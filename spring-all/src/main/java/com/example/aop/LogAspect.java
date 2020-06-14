package com.example.aop;

import com.example.annotation.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(com.example.annotation.Action)")
    public void  annotationPointcut(){};

    @Pointcut("execution(* com.example.service.DemoMethodService.*(..))")
    public void  methodPointcut(){};

    @After("annotationPointcut()")
    public  void  after(JoinPoint joinPoint){
        MethodSignature  signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action  action= method.getAnnotation(Action.class);
        System.out.println("注解式拦截  " + action.name());
    }

    @Before("methodPointcut()")
    public void  before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法式拦截 " + method.getName());
    }
}
