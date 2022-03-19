<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/18
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>RestFull风格的URL请求</title>
</head>
<body>
<fieldset>
    <legend>@PathVariable注解 RestFull风格的URL请求</legend>
    <form action="">
        姓名： <input type="text" name="name" id="name">
        年龄： <input type="number" name="age" id="age">
        <button type="button" id="btn">提交</button>
    </form>
</fieldset>
<script src="${ctx}/js/jquery.min.js"></script>
<script>
    $(function (){
        $("#btn").click(function (){
            var username=$("#name").val();
            var userage=$("#age").val();
            location.href="${ctx}/indexController/PathVariabl/"+username+"/"+userage+"";
        });
    })

</script>
</body>
</html>
