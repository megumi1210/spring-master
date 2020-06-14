package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *  异步任务实现
 */
@Service
public class AsyncTaskService {

    @Async //表明是个异步方法，如果注解在类上表明此类下的全部方法都是异步方法
    public  void  executeAsyncTask(Integer i){
        System.out.println("执行异步任务：" + i);
    }

    @Async
    public void  executeAsyncTaskPlus(Integer i){
        System.out.println("执行异步任务+1 ：" +(i+1) );
    }
}
