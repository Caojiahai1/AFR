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
    <link rel="stylesheet" href="/tools/loading.css">
    <script type="text/javascript" src="/tools/tools.js?v1"></script>
    <link rel="stylesheet" href="/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/bootstrap/3.3.5/css/bootstrap.min.back.css">
    <script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <%--<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.css"></script>--%>
    <%--<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <%--<link rel="stylesheet" href="/bootstrap/fileinput/fileinput.min.css">--%>
    <%--<script type="text/javascript" src="/bootstrap/fileinput/fileinput.min.js"></script>--%>
    <%--<script type="text/javascript" src="/bootstrap/fileinput/locale/zh.js"></script>--%>
    <script type="text/javascript" src="/js/main.js?v1"></script>
    <script type="text/javascript" src="/js/filehelper.js?v1"></script>
    <script type="text/javascript" src="/js/jsonhelper.js?v1"></script>

    <sitemesh:head/><!--会把被过滤页面head里面的东西（除了title）放在这个地方-->
</head>
<body>
<%--<%@ include file="/common/head.jsp"%>--%>

<div>
    <sitemesh:body/><!--被过滤的页面body里面的内容放在这里。-->
</div>

<%-- 自定义弹出提示框 --%>
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title"><strong id="myModalLabel"></strong></h4>
            </div>
            <div class="modal-body" id="myModalBody" align="center"></div>
            <div class="modal-footer">
                <button id="modalButton" type="button" class="btn btn-default" data-dismiss="modal">确定</button>
                <%--<button type="button" class="btn btn-primary">提交更改</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<%--<%@ include file="/common/foot.jsp"%>--%>
</body>
</html>