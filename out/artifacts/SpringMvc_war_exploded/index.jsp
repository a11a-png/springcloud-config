<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/16
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${ctx}为jstl标签代表lo  twoController对应的控制器，login对应的方法  --%>
<a href="${ctx}/twoController/login">twoController (跳转)</a><br>
<a href="${ctx}/indexController/login">indexController (跳转)</a><br>
<a href="${ctx}/indexController/MAV">ModelAndView (跳转)</a><br>
<a href="${ctx}/indexController/Voidfun">Voidfun (跳转)</a><br>
<a href="${ctx}/indexController/ajaxfun">ajaxTJ (跳转)</a><br>
<a href="${ctx}/indexController/PathVariable">PathVariable注解 (跳转)</a><br>
<a href="${ctx}/twoController/requestData">requestData传递数据 (跳转)</a><br>
<a href="${ctx}/twoController/ModelData">ModelData传递数据 (跳转)</a><br>
<a href="${ctx}/twoController/ModelAndViewData">ModelAndViewData传递数据 (跳转)</a><br>
<a href="${ctx}/indexController/FileUpload">FileUpload文件上传 (跳转)</a><br>

<fieldset>
    <legend>自动类型匹配</legend>
    <form action="${ctx}/indexController/autoGetParam" method="get">
        用户名： <input type="text" name="userName">
        密码： <input type="password" name="password">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>自动封装</legend>
    <form action="${ctx}/indexController/pojoGetParam" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>request获取参数</legend>
    <form action="${ctx}/indexController/requestGetParam" method="post">
        车型： <input type="text" name="car">
        颜色： <input type="text" name="color">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>@RequestParam注解获取参数</legend>
    <form action="${ctx}/indexController/atRequestParam" method="post">
        价钱： <input type="text" name="money">
        座驾： <input type="number" name="zy">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>forward转发 ModelAndView形式</legend>
    <form action="${ctx}/atForward/forwardfun" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>forward转发2 String形式</legend>
    <form action="${ctx}/atForward/forwardfun2" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>重定向 String形式</legend>
    <form action="${ctx}/atRedirect/redirectfun" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>重定向 ModelAndView形式</legend>
    <form action="${ctx}/atRedirect/redirectfun2" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>重定向传参 String形式</legend>
    <form action="${ctx}/atRedirect/redirectfun3" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>重定向传参 ModelAndView形式</legend>
    <form action="${ctx}/atRedirect/redirectfun4" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>重定向传参 ModelAndView 参数不拼接到路径的形式</legend>
    <form action="${ctx}/atRedirect/redirectAttributesNoUrlParam" method="post">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>@ModelAttribute注解</legend>
    <form action="${ctx}/atModelAttr/one" method="get">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>@ModelAttribute注解在参数中</legend>
    <form action="${ctx}/atModelAttr/two" method="get">
        姓名： <input type="text" name="name">
        年龄： <input type="text" name="age">
        身高： <input type="text" name="tall">
        <button type="submit">提交</button>
    </form>
</fieldset>

</body>
</html>
