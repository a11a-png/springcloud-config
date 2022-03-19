<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/19
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>成功页面</title>
</head>
<body>
<button id="btn">获取session数据</button>

<fieldset>
    <legend>name:${name}</legend>
    <div>user:${user}</div>
</fieldset>


<fieldset>
<legend>重定向传String形式参数</legend>
<div>msgM:${msgM}</div>
<div>msgR:${msgR}</div>
<div>user:${user}</div>
</fieldset>

<fieldset>
    <legend>ModelAttribute注解</legend>
    <div>msg:${msg}</div>
    <div>msg2:${msg2}</div>
    <div>msgr:${msgr}</div>
    <div>users:${users}</div>
    <div>user:${user}</div>
    <div>user01:${user01}</div>
    <div>user02:${user02}</div>
    <div>user03:${user03}</div>
    <div>role:${role}</div>
    <div>Role:${Role}</div>
    <div>re:${re}</div>
</fieldset>

<fieldset>
    <legend>单文件上传:${msgM}</legend>
    <a href="${ctx}/fileUpload/download/${singleFileName}">下载${singleFileName}</a>
</fieldset>

<fieldset>
    <legend>多文件上传:${msgMs}</legend>
    <c:forEach items="${singleFileNames}" var="filename">
        <a href="${ctx}/fileUpload/download/${filename}">下载${filename}</a>
    </c:forEach>
</fieldset>

<script src="${ctx}/js/jquery.min.js"></script>
<script>
     $(function (){
         $("#btn").click(function (){
             $.post("${ctx}/SessionAttribute/getSession",{},function (jsonData){
                   console.log(jsonData);
             });
         });
     });
</script>
</body>
</html>
