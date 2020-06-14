<%--
  Created by IntelliJ IDEA.
  User: megumi
  Date: 2020/6/5
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
       <script src="static/js/jquery-3.5.1.min.js"></script>
       <script>

           //通过 ajax 循环发送请求
           deferred();

            function deferred() {
               $.get('defer',function (data) {
                     console.log(data);
                     deferred();
               })
            }
       </script>
</body>
</html>
