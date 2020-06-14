<%--
  Created by IntelliJ IDEA.
  User: megumi
  Date: 2020/6/4
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
      <div class="upload">
          <form action="upload" enctype="multipart/form-data" method="post">
              <input type="file" name="file"><br>
              <input type="submit" value="上传">
          </form>
      </div>
</body>
</html>
