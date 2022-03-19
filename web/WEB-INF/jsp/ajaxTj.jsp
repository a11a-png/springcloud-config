<%--
  Created by IntelliJ IDEA.
  User: Kyrie
  Date: 2021/8/18
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--&lt;%&ndash; var：变量名 value：值  scope:作用范围  ${pageContext.request.contextPath}：EL属性代表项目名 &ndash;%&gt;--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="page" />
<html>
<head>
    <title>ajax</title>
</head>
<body>
<fieldset>
    <legend>ajax提交</legend>
    <button type="button" id="btn">提交</button>
</fieldset>

<script src="${ctx}/js/jquery.min.js"></script>
<script>
$("#btn").click(function (){
   var arrdata=[];
   //24×3600×1000一天的时间搓
   var old={name:"0001",age:20,tall:170,Birthday:"2021-08-18 10:30:00"}
   var now={name:"0002",age:21,tall:171,Birthday:"2021-08-17 10:30:00"}
    arrdata.push(old);
    arrdata.push(now);
    console.log(arrdata);
  $.ajax({
      type:"post",
      url:"${ctx}/indexController/ajaxjsData",
      contentType:"application/json;charset=utf-8",
      //如果提交的是json数据类型，则必须有此参数,表示提交的数据类型
      /* 传一个对象集合 */
      data:JSON.stringify(arrdata),  //将数据转换为JSON格式
      dataType:"json",
      success:function (jsondata){
          console.log(jsondata);
      }
  });


});

</script>
</body>
</html>
