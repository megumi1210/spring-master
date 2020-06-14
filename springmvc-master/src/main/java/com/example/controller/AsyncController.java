package com.example.controller;

import com.example.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;


/**
 *   Servlet 异步方法实现推送
 */
@Controller
public class AsyncController {
    @Autowired
    PushService  pushService;  //定时任务，定时更新DeferredResult

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall(){//给客户端返回 DeferredResult
         return  pushService.getAsyncUpdate();
    }

}
