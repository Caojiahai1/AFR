<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>

    <title><sitemesh:title/></title>  <!-- 会自动替换为被过滤页面的title.sitemesh:title可选-->
    <style type="text/css">
        html,body {
            height: 100%;
            width:100%;
            margin: 0px;
            padding: 0px;
        }
    </style>
    <link rel="stylesheet" href="/bootstrap/3.3.5/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/main.js"></script>

    <sitemesh:head/><!--会把被过滤页面head里面的东西（除了title）放在这个地方-->
</head>
<body>
<%--<%@ include file="/common/head.jsp"%>--%>

<div>
    <sitemesh:body/><!--被过滤的页面body里面的内容放在这里。-->
</div>
<%--<%@ include file="/common/foot.jsp"%>--%>
</body>
</html>