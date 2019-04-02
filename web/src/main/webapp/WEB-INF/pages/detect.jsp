<%@page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <style>
        .mainImg{
            width:460px;
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
    </style>
</head>
<body>
<div class="page-header" align="center">
    <h1 style="    font-size: 24px;
    font-family: PingFangSC-Semibold,Microsoft YaHei;
    font-weight: 600;">功能展示</h1>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-5" style="background-color: #f6f7fb">
            <div class="fakeimg" style="width: 100%;height: 450px" align="center">
                <img id="mainImg" src="/img/1.jpg"  class=".img-responsive mainImg">
            </div>
            <div style="padding-top: 10px;margin-bottom: 10px" align="center">
                <form class="form-inline" role="form">
                    <div class="input-group">
                        <input id="lefile" name="lefile" type="file" style="display:none">
                        <button type="button" class="btn btn-primary" onclick="$('input[id=lefile]').click();">
                            <span class="glyphicon glyphicon-download-alt"></span> 上传图片
                        </button>
                        <%--<span class="input-group-addon" onclick="$('input[id=lefile]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="fa fa-folder-open"></i>Browse</span>--%>
                        <%--<input id="file-Portrait" type="file" class="file-loading">--%>
                    </div>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="网络图片Url">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="model.detectNetImage()">Go!</button>
                    </span>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-7">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        检测结果
                    </a>
                </li>
                <li><a href="#ios" data-toggle="tab">返回JSON</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="home">
                    <p>菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
                </div>
                <div class="tab-pane fade" id="ios">
                    <pre id="jsonResponse"></pre>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/model/detectface/detect.js?V3"></script>
</body>
</html>
