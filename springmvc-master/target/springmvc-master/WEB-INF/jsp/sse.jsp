<%--
  Created by IntelliJ IDEA.
  User: megumi
  Date: 2020/6/5
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <div id="msgFromPush"></div>
     <script src=" <c:url value="static/js/jquery-3.5.1.min.js"/>"></script>
     <script>
         if(!!window.EventSource){// EventSource 是 SSE的客户端
             const source = new EventSource('push');
             let s = '';
             source.addEventListener('message',function (e) {//添加客户端的监听
                     s+=e.data+"<br/>";
                     $("#msgFromPush").html(s);
             });

             source.addEventListener('open',function (e) {
                      console.log("连接打开...");
             },false);

             source.addEventListener('error',function (e) {
                  if(e.readyState === EventSource.CLOSED){
                       console.log("连接关闭");
                  }else {
                       console.log(e.readyState);
                  }
             },false);
         }else {
              console.log("你的浏览器不支持SSE")
         }
     </script>


</body>
</html>
