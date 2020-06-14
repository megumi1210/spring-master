<%--
  Created by IntelliJ IDEA.
  User: megumi
  Date: 2020/6/5
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
         <div id="resp"></div>
         <input type="button" onclick="req();" value="请求">
         <script src="static/js/jquery-3.5.1.min.js"></script>
         <script>
              function  req() {
                      $.ajax({
                          url:"convert",
                          data:"1-megumi",
                          type:"post",
                          contentType:"application/x-wisely",
                          success:function (data) {
                               $("#resp").html(data)
                          }
                      });
              }
         </script>
</body>
</html>
