package com.example.controller;

import com.example.websocket.SystemWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

@Controller
public class WebSocketController {
    @Autowired
    SystemWebSocketHandler handler;

    @RequestMapping("login/{username}")
    public @ResponseBody String login(HttpSession session , @PathVariable("username")String username){
        System.out.println("登录接口,username=" + username);
        session.setAttribute("username",username);
        return  "成功";
    }

    @RequestMapping("/message")
   public  @ResponseBody String  sendMessage(){
           boolean flag = handler.sendMessageToAllUsers( new TextMessage("你好"));
          System.out.println(flag);
           return "发送";
   }

}
