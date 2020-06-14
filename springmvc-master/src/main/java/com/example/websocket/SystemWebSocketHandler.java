package com.example.websocket;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class SystemWebSocketHandler extends TextWebSocketHandler {
    //在线用户列表
    private  static  final Map<String, WebSocketSession>  users;
    //用户标识
    private  static  final String  CLIENT_ID ="username";

    static {
         users = new HashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功建立连接");
        String username = getClientId(session);
        System.out.println("连接的用户Id为: " + username);
        if(username !=null){
            users.put(username,session);
            session.sendMessage(new TextMessage("成功建立连接"));
            System.out.println(username);
            System.out.println(session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //业务处理
        System.out.println("服务端收到消息:"+message.getPayload());
        String username = getClientId(session);
        WebSocketMessage<String> msg = new TextMessage("欢迎 " +username);

        try{
             session.sendMessage(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  发型信息给指定用户
     * @param clientId  用户id
     * @param message   发送的消息
     * @return 是否发送成功
     */
    public  boolean  sendMessageToUser(String clientId , TextMessage message){
        if(users.get(clientId)==null){
            System.out.println(clientId +" 不存在");
            return  false;
        }

        WebSocketSession session =  users.get(clientId);
        System.out.println("sendMessage:" +session);
        if(!session.isOpen()){
            System.out.println("会话已结束");
            return  false;
        }
        try{
             session.sendMessage(message);
        }catch (IOException e){
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    /**
     *  广播消息
     * @return 是否全体发送成功
     */
    public boolean sendMessageToAllUsers(TextMessage message){
         boolean  allSendSuccess = true;
        Set<String> clientIds = users.keySet();
        WebSocketSession session = null;
        for(String  clientId :clientIds){
             try{
                  session = users.get(clientId);
                  if(session.isOpen()){
                       session.sendMessage(message);
                  }
             }catch (IOException e){
                 e.printStackTrace();
                 allSendSuccess =false;
             }
        }

        return  allSendSuccess;
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
          if(session.isOpen()){
              session.close();
          }
        System.out.println("连接出错");
         users.remove(getClientId(session));
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("连接已关闭" + status);
        users.remove(getClientId(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return  false;
    }

    /**
     *  获取用户标识
     */
    private  String  getClientId(WebSocketSession session){
        try{
            return (String) session.getAttributes().get(CLIENT_ID);
        }catch (Exception e){
            return  null;
        }
    }



}
