
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

%>
<base href="<%=basePath%>">

<html>
<head>
  <title>zsdk管理后台</title>

  <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/js/plugins/easyui/themes/color.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/css/u8server.css">

  <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/js/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

  <script type="text/javascript">

    /**
     * 创建新选项卡
     * @param tabId    选项卡id
     * @param title    选项卡标题
     * @param url      选项卡远程调用路径
     */
    function addTab(tabId,title,url){
      //如果当前id的tab不存在则创建一个tab
      if($("#"+tabId).html()==null){
        var name = 'iframe_'+tabId;
        $('#centerTab').tabs('add',{
          title: title,
          closable:true,
          cache : false,
          //注：使用iframe即可防止同一个页面出现js和css冲突的问题
          content : '<iframe name="'+name+'"id="'+tabId+'"src="'+url+'" width="100%" height="100%" frameborder="0" scrolling="auto">'+'</iframe>'
        });
      }
    }

  </script>

</head>
<body class="easyui-layout">
  <!-- 正上方panel -->
  <div region="north" style="height:102px" href="<%=basePath%>/header.jsp">
  </div>
  <!-- 正左边panel -->
  <div region="west" title="菜单栏" split="true" style="width:200px;padding1:1px;overflow:hidden;">
    <div class="easyui-accordion" fit="true" border="false">
      <!-- selected -->

      <div title="操作" selected="true">
        <ul>
          <li><a class="easyui-linkbutton c1 u8_menu"  href="javascript:addTab('tabId_account','游戏信息','<%=basePath%>/data/showByDate');">账号信息总览</a></li>
          <li><a class="easyui-linkbutton c1 u8_menu"  href="javascript:addTab('tabId_roles','角色管理','<%=basePath%>/admin/adminRoles');">角色管理</a></li>
        </ul>
      </div>

    </div>
  </div>
  <!-- 正中间panel -->
  <div region="center" title="功能区" >
    <div class="easyui-tabs" id="centerTab" fit="true" border="false">
      <div title="欢迎页" style="padding:20px;overflow:hidden;">
        <div style="margin-top:20px;">
          <h3>你好，欢迎来到【zsdk管理后台】</h3>
        </div>
      </div>
    </div>
  </div>
  <!-- 正下方panel -->
  <div region="south" style="height:50px;padding-top: 7px" align="center">
    <label>
      zsdk网络<br/>
      zsdk网站：<a href = "http://www.zsdk.com">http://www.zsdk.com</a>
    </label>
  </div>
</body>
</html>
