<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/22
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<fieldset>
    <legend>单文件上传</legend>
    <form action="${ctx}/fileUpload/Upload" enctype="multipart/form-data" method="post">
        选择文件<input type="file" name="file"><br>
        其他参数<input type="text" name="name"><br>
        <button type="submit">提交</button>
    </form>
</fieldset>

<fieldset>
    <legend>多文件上传</legend>
    <form action="${ctx}/fileUpload/Uploads" enctype="multipart/form-data" method="post">
        <%-- multiple="multiple" 允许多文件上传 --%>
        选择文件<input type="file" name="files" multiple="multiple"><br>
        其他参数<input type="text" name="name"><br>
        <button type="submit">提交</button>
    </form>
</fieldset>
<script src="${ctx}/js/jquery.min.js"></script>
<script>
</script>
</body>
</html>
