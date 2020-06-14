package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class TaskScheduledConfig {

//    public TaskScheduler taskScheduler(){
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(8);
//        taskScheduler.setThreadNamePrefix("scheduled-thread-");
//        taskScheduler.initialize();
//        return  taskScheduler;
//    }
}
