<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by LZHR.
  User: lizhongren1
  Date: 2018/3/7
  Time: 上午11:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<body>

<h1>首页</h1>


<a href="/logout">登出</a>

<shiro:hasRole name="CTO">
    CTO才能看到
</shiro:hasRole>

<shiro:hasRole name="CEO">
    CEO才能看到
</shiro:hasRole>

</body>
</html>
