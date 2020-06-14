package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  计划任务实现
 *  todo  solve problem
 */
@Service
public class ScheduledTaskService {

    private  static  final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate=3000)  //每隔固定时间执行
    public  void reportCurrentTime(){
        System.out.println("每隔3秒执行一次 " + sdf.format(new Date()));
    }

   @Scheduled(cron ="0 41 14 ? * *")  //按照指定时间执行，本例指每天 14 ： 41分执行 不能共存
    public  void  fixTimeExecution(){
        System.out.println("在指定的时间 " + sdf.format(new Date()) +"执行");
    }
}
