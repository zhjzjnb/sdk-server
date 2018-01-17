<%--
  Created by IntelliJ IDEA.
  User: zhj
  Date: 18/1/17
  Time: 下午12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

%>
<base href="<%=basePath%>">
<html>
<head>

    <!-- 引入JQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui/jquery.min.js"></script>
    <!-- 引入EasyUI -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui/jquery.easyui.min.js"></script>
    <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.md5.js"></script>

    <!-- 引入EasyUI的样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/default/easyui.css"
          type="text/css"/>
    <!-- 引入EasyUI的图标样式文件-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-easyui/themes/icon.css" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">


    <title>登录</title>
</head>
<body>
<div class="login_wrap">
    <div style="overflow: hidden;height:100%;display: block;">
        <div style="width: 680px; height: 100px; margin-left:auto;margin-right: auto; margin-top: 180px;">
        </div>
        <div style="margin-left:auto;margin-right: auto;margin-top: 30px;">
            <form id="login2" method="post">
                <label>用户名：<input id="username" type="text" name="userName"/></label>
                <label style="margin-top: 20px;">密　码：<input id="pwd" type="password" name="pass"/></label>
                <button id="btnLogin" type="button" onclick="javascript:login();">登　录</button>
            </form>
        </div>
    </div>


</div>

<script type="text/javascript">

    function login() {

        var username = $("#username").val();
        var pwd = $("#pwd").val();
        pwd = $.md5(pwd);

        $.post('<%=basePath%>/admin/doLogin', {username: username, password: pwd}, function (result) {
            if (result.state == 1) {

                location.href = "<%=basePath%>/admin/index"

            } else {
                alert(result.msg)
            }

        }, 'json');


    }


    $("#pwd").keyup(function (event) {
        if (event.keyCode == 13) {
            $("#btnLogin").click();
        }
    });

</script>


</body>
</html>
