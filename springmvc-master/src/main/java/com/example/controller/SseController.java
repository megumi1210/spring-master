package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**               服务端推送技术一 SSE
 *   SSE Server Send Event  服务端发送事件 基于 Servlet3.0+ 异步方法特性
 *   需要新式浏览器的支持
 *    原理 客户端向服务端发送数据，服务端会抓住这个请求不放，等到有数据更新时候
 *    才返回给客户端，当客户端收到消息后，再向服务端发送请求周而复始
 *
 */
@Controller
public class SseController {

    /*
      每隔3秒钟向李安澜起推送随机消息
     */

    @RequestMapping(value="/push" ,produces = "text/event-stream;charset=UTF-8")//服务端SSE的支持
    public @ResponseBody String push(){
        Random r = new Random();
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  "data:Testing 1,2,3" + r.nextInt() +"\n\n";
    }
}
