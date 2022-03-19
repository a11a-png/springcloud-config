<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/17
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getCs</title>
</head>
<body>
<div class="one">
    <span>自动匹配参数类型</span>
    <span>userName:${userName}</span>
    <span>password:${password}</span>
</div>

<div class="two">
    <span>自动封装</span>
    <span>name:${Name}</span>
    <span>age:${Age}</span>
</div>

<div class="three">
    <span>request获取参数</span>
    <span>car:${car}</span>
    <span>color:${color}</span>
</div>

<div class="four">
    <span>@RequestParam注解获取参数</span>
    <span>money:${money}</span>
    <span>座驾:${zy}</span>
</div>

<div class="five">
    <span>@PathVariable注解 RestFull风格的URL请求</span>
    <span>name:${name}</span>
    <span>age:${age}</span>
</div>

</body>
</html>
