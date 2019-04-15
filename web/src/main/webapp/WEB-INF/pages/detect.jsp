<%@page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <style>
        .mainImg{
            width: auto;
            height: auto;
            max-height: 100%;
            max-width: 100%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .mainDiv{
            width: 100%;
            height: 450px;
            margin-top: 10px;
            padding: 0px;
        }
        .listDiv{
            width: 100%;
            height: 150px;
            margin-top: 10px
        }
        .elementDiv{
            width: 30%;
            height: 100%;
            border: solid #00b2e0 2px;
        }
        .elementRight{
            margin-left: 5%;
        }
        .mask{
            filter: alpha(opacity=50);
            opacity: 0.5;
            background-color: #9D9D9D;
        }
        .resultImg{
            width:110px;
            height: 110px;
            background-repeat:no-repeat;
            float: left;
            margin-left: 15px;
            /*border: solid 2px #00b2e0*/
        }
        .resultImgList{
            width: 100%;
            height: 130px;
            border-bottom: solid lightgrey 1px;
            margin-top: 20px;
        }
        .attribute{
            margin-left: 20px;
            font-size: 14px;
            color: #3f3f4c;
            line-height: 54px;
        }
        .attribute th{
            width: 150px;
            font-weight: bolder;
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
        <div id="left" class="col-sm-5" style="background-color: #f6f7fb;">
            <div id="mainDiv" class="col-sm-12 mainDiv">
                <img id="mainImg" src=""  class=".img-responsive mainImg">
            </div>
            <div class="col-sm-12 listDiv">
                <div class="col-sm-4 elementDiv mask">
                    <img id="img1" localPath="" src="https://www.nvsay.com/uploads/allimg/180714/210-1PG41953060-L.jpg"  class=".img-responsive mainImg">
                </div>
                <div class="col-sm-4 elementDiv mask elementRight">
                    <img id="img2" localPath="" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554486870858&di=bedbbfe9e4a3cf6aa4b65e41b22bfcb7&imgtype=0&src=http%3A%2F%2Fwww.sinaimg.cn%2Fdy%2Fslidenews%2F2_img%2F2013_32%2F730_1037532_155886.jpg"  class=".img-responsive mainImg">
            </div>
                <div class="col-sm-4 elementDiv mask elementRight">
                    <img id="img3" localPath="" src="http://pic.rmb.bdstatic.com/b89fd326ce445ae01475f236b8f83c23.png@wm_2,t_55m+5a625Y+3L+aiqOiKsemUmei/h+S6huiKseWtow==,fc_ffffff,ff_U2ltSGVp,sz_18,x_12,y_12"  class=".img-responsive mainImg">
                </div>
            </div>
            <div class="col-sm-12" style="padding-top: 10px;margin-bottom: 10px" align="center">
                <div class="form-inline" role="form">
                    <div class="input-group">
                        <input id="lefile" name="lefile" type="file" style="display:none">
                        <button type="button" class="btn btn-primary" onclick="$('input[id=lefile]').click();">
                            <span class="glyphicon glyphicon-download-alt"></span> 上传图片
                        </button>
                        <%--<span class="input-group-addon" onclick="$('input[id=lefile]').click();" style="cursor: pointer; background-color: #e7e7e7"><i class="fa fa-folder-open"></i>Browse</span>--%>
                        <%--<input id="file-Portrait" type="file" class="file-loading">--%>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="input-group">
                        <input id="NetUrl" type="text" class="form-control" placeholder="网络图片Url">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="detectModel.detectNetImage()">检索</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <div id="right" class="col-sm-7">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#home" data-toggle="tab">
                        检测结果
                    </a>
                </li>
                <li><a href="#json" data-toggle="tab">返回JSON</a></li>
            </ul>
            <div id="myTabContent" class="tab-content" style="border: solid 1px lightgrey;border-top:1px solid hsla(0,0%,90%,.5);">
                <div class="tab-pane fade in active" id="home">
                    <div id="resultImgList" class="resultImgList">
                    </div>
                    <div id="attributes">
                    </div>
                </div>
                <div class="tab-pane fade" id="json">
                    <div id="jsonResponse" style="overflow: scroll"></div>
                    <%--<pre id="jsonResponse"></pre>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/model/detectface/detect.js?V3"></script>
</body>
</html>
