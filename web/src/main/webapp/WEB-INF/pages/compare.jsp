<%@page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <style>
        .compareDiv{
            width: 49%;
            height: 300px;
            margin-top: 10px;
            padding: 0px;
        }
        .compareDivBorder{
            border: solid 1.5px #00b2e0;
        }
        .compareDiv2{
            margin-left: 2%;
        }
        .faceListDiv{
            width: 49%;
            height: 358px;
            margin-top: 10px;
            padding: 0px;
            border: solid 1px lightgrey;
        }
        .faceListDiv2{
            margin-left: 2%;
        }
        .compareImg{
            width: auto;
            height: auto;
            max-height: 100%;
            max-width: 100%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .resultImg1,.resultImg2{
            width:44%;
            height: 44%;
            background-repeat:no-repeat;
            float: left;
            margin-left: 4%;
            margin-top: 4%;
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
    <div class="row" style="width: 100%">
        <div id="left" class="col-sm-8" style="background-color: #f6f7fb;">
            <div id="imagediv1" imageNum = 1 class="col-sm-6 compareDiv">
                <img id="image1" localpath="" src="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2086958889,806840009&fm=11&gp=0.jpg"  class=".img-responsive compareImg">
            </div>
            <div id="imagediv2" imageNum = 2 class="col-sm-6 compareDiv compareDiv2">
                <img id="image2" localpath="" src="https://www.nvsay.com/uploads/allimg/180714/210-1PG41953060-L.jpg"  class=".img-responsive compareImg">
            </div>
            <div id="image1Result" class="col-sm-6 faceListDiv">
            </div>
            <div id="image2Result" class="col-sm-6 faceListDiv faceListDiv2">
            </div>
            <div class="col-sm-12" style="padding-top: 10px;margin-bottom: 10px" align="center">
                <div class="form-inline" role="form">
                    <div class="input-group">
                        <input id="lefile" name="lefile" type="file" style="display:none">
                        <button type="button" class="btn btn-primary" onclick="$('input[id=lefile]').click();">
                            <span class="glyphicon glyphicon-download-alt"></span> 上传图片
                        </button>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="input-group">
                        <input id="compareNetUrl" type="text" class="form-control" style="width: 300px" placeholder="网络图片Url">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="compareModel.detectNetImage()">检索</button>
                        </span>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="btn btn-success" type="button" onclick="compareModel.doCompare()">
                        <span class="glyphicon glyphicon-random"></span> 进行比对
                    </button>
                </div>
            </div>
        </div>
        <div id="right" class="col-sm-4">
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
                    <div id="result">
                        <p id="analysisResult" align="center" style="font-size: 18px;margin-top: 25%;"></p>
                    </div>
                </div>
                <div class="tab-pane fade" id="json">
                    <div id="jsonResponse" style="overflow: scroll"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/model/compareface/compareface.js?V1"></script>
</body>
</html>
