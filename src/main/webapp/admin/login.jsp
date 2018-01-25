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
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<base href="<%=basePath%>">
<html>
<head>
    <title></title>

    <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/u8server.css">

    <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.md5.js"></script>

</head>
<body>
<div style="height: 220px;display: block;">

</div>

<div id="dialog_add" class="easyui-dialog" title="用户登录"
     closed="false" buttons="#dlg-buttons" style="height: 180px;width:340px;margin: 0 auto;">
    <form id="fm" method="post" novalidate>
        <div class="u8_form_row" style="margin-top: 15px">
            <label style="width: 50px">用户名：</label>
            <input id = "username" type="text" class="easyui-textbox" name="username" maxlength="255"  />
        </div>

        <div class="u8_form_row" >
            <label style="width: 50px">密　码：</label>
            <input id="pwd" type="password" class="easyui-textbox" name="password" maxlength="255" />
        </div>

    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" id="btnLogin" class="easyui-linkbutton c6" onclick="login();" style="width:90px">登　录</a>
</div>

<script type="text/javascript">

    function login() {

        var username = $("#username").val();
        var pwd = $("#pwd").val();
        pwd = $.md5(pwd);
//        use ajax to post
        $.ajax({
            type:"POST",
            url:'<%=basePath%>/admin/doLogin',
            contentType:"application/json",  //发送信息至服务器时内容编码类型。
            dataType:"json",  // 预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如XML MIME类型就被识别为XML。
            data:JSON.stringify({username: username, password: pwd}),
            success:function(result){
                if (result.state == 0) {
                    location.href = "<%=basePath%>"
                } else {
                    $.messager.show({
                        title:'操作提示',
                        msg:result.msg
                    })
                }
            }
        });
    }


</script>

</body>
</html>

