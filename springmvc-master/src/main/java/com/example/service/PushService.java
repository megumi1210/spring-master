package com.example.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
    //类似于Future 延迟的结果
    private DeferredResult<String> deferredResult;

    public DeferredResult<String> getAsyncUpdate(){
         deferredResult = new DeferredResult<>();
         return deferredResult;
    }

    @Scheduled(fixedDelay = 3000)//定时任务
    public  void refresh(){
         if(deferredResult !=null){
              deferredResult.setResult(Long.toString(System.currentTimeMillis()));
         }
    }
}
