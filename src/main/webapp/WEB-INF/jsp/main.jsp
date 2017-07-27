<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>bbs论坛</title>
</head>
<body>
${user.userName},<br><br>欢迎您进入bbs论坛，<br><br>您当前积分为${user.credits}。
<br><br>
<input type="button" value="退出" onClick="javascript:location.href='/bbs'">
</body>
</html>