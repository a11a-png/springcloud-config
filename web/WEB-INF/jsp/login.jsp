<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/16
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>login</title>
</head>
<body>
login msg:${msg}
<a href="${ctx}">index</a>
<button id="btn" onclick="fun()">returnJson</button>
<button id="btn2" onclick="fun2()">returnJson2</button>
<fieldset>
    <legend>登录系统</legend>
    <form>
        用户名：<input type="text" name="userName" id="userName">
        密码：<input type="password" name="password" id="password">
        <button type="button" id="btn3">登录</button>
    </form>
</fieldset>


<script src="${ctx}/js/jquery.min.js" charset="utf-8"></script>
<script>
    function fun(){
        $.post("${ctx}/indexController/returnJson",{},function (json){
            console.log(json);
        });
    }

    function fun2(){
        $.post("${ctx}/indexController/returnJson2",{},function (json){
            console.log(json);
        });
    }

    $("#btn3").click(function () {
        var userName = $("#userName").val();
        var password = $("#password").val()
        $.post("${ctx}/SessionAttribute/sessionLogin",
            {
                userName: userName,
                password: password,
            },
            function (jsonData) {
                console.log(jsonData);
                if (jsonData != "" && jsonData!=null) {
                    //跳转页面
                    location.href = "${ctx}/SessionAttribute/succeed";
                } else {
                    alert("登录失败");
                }
            }
        )})
</script>
</body>
</html>
