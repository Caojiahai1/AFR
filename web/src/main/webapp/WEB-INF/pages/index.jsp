<%@page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>人脸识别系统</title>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation" style="margin-bottom: 0px">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">人脸识别</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="#/detect" url="/face/detect">人脸检测</a></li>
                <li><a href="#/compare" url="/face/compare">人脸比对</a></li>
                <li><a href="#/search" url="/face/search">人脸搜索</a></li>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown">--%>
                        <%--个人中心 <b class="caret"></b>--%>
                    <%--</a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="#">个人信息</a></li>--%>
                        <%--<li><a href="#">设置</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <%--<li><a href="#">使用指南</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <%--<li><a href="#">另一个分离的链接</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            </ul>
        </div>
    </div>
</nav>
<div style="width: 100%;top: 50px;bottom: 5px;position:absolute;background-color: #2aabd2">
    <iframe src="/index.jsp" width="100%" height="100%" style="border: 0px">

    </iframe>
</div>

</body>
</html>
