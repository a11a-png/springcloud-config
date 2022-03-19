<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/18
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>传递数据</title>
</head>
<body>
    <c:forEach var="str" items="${users}">
       <div>${str}</div>
    </c:forEach>
</body>
</html>
