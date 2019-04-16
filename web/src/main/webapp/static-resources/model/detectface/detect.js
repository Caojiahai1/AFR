var DetectView = {};

DetectView.search = function (params) {
    var base = this;

    this.imgList = {};
    //每个人脸html
    this.attributes = {};

    //向图片列表插入新检测的图片
    this.addImg = function (url, localPath) {
        if (!base.checkHasImg(url)) {
            return;
        }
        $("#img1").attr("src", url);
        $("#img1").attr("localPath", localPath);
        $("#img2").attr("src", base.imgList.img1.src);
        $("#img2").attr("localPath", base.imgList.img1.localPath);
        $("#img3").attr("src", base.imgList.img2.src);
        $("#img3").attr("localPath", base.imgList.img2.localPath);
        base.initImgList();
    }

    //检查图片是否已存在
    this.checkHasImg = function (src) {
        if (src == '' || src == base.imgList.img1.src || src == base.imgList.img2.src || src == base.imgList.img3.src) {
            return false;
        }
        return true;
    }

    //初始化图片列表
    this.initImgList = function () {
        base.imgList = {
            img1 : {
                src : $("#img1").attr("src"),
                localPath : $("#img1").attr("localPath")
            },
            img2 : {
                src : $("#img2").attr("src"),
                localPath : $("#img2").attr("localPath")
            },
            img3 : {
                src : $("#img3").attr("src"),
                localPath : $("#img3").attr("localPath")
            }
        }
    }

    //检索网络url
    this.detectNetImage = function () {
        var netUrl = $("#NetUrl").val();
        if (!FileHelper.checkNetURL(netUrl)) {
            mainWindow.alert("warn", "请输入正确的图片路径！");
            $("#NetUrl").val("");
            return;
        }
        base.detectImage({netUrl : netUrl});
        $("#mainImg").attr("src", netUrl);
        base.addImg(netUrl, "");
        base.refreshMask();
    }
    
    //ajax请求后台检测接口
    this.detectImage = function (param) {
        $.ajax({
            url: "/DetectFace/detectImage?random=" + Math.random(),
            type: "json",
            method: "post",
            data: param,
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    base.showResponseJson(data.object);
                    base.showDetectResult(data.object);
                } else {
                    mainWindow.alert("fail", data.message);
                }
            },
            beforeSend: function () {
                myTools.circleLoading("mainDiv");
            },
            complete: function () {
                myTools.circleLoadingOver("mainDiv");
            }
        });
    }


    //上传图片
    this.fileUpload = function () {
        var data = {detectFace : true};
        var json = JSON.stringify(data);
        FileHelper.imageUpload("lefile", {json : json}, uploadImageCallback);
        base.clearFaceOutLine();
    }

    //上传图片回调函数
    var uploadImageCallback = function (result) {
        if (result.success) {
            if (result.object) {
                base.addImg($("#mainImg").attr("src"), result.object.localPath);
                base.showResponseJson(result.object.json);
                base.showDetectResult(result.object.json);
            }
        } else {
            mainWindow.alert("fail", result.message);
        }
        base.refreshMask();
    }

    //绑定上传图片事件
    this.bindFileChange = function () {
        $("#lefile").change(function () {
            if (this.files[0] != undefined) {
                base.fileUpload();
                $("#mainImg").attr("src", FileHelper.getObjectURL(this.files[0]));
            }
            // change事件触发一次后失效bug
            base.bindFileChange();
        })
    }

    //展示返回json
    this.showResponseJson = function (json) {
        $("#jsonResponse").html(formatJson(JSON.stringify(json)), 6);
    }

    //imglist绑定点击事件
    this.bindImgListClick = function () {
        $(".elementDiv").click(function () {
            base.refreshMask($(this));
            base.clearFaceOutLine();
            //img元素
            var imgElement = $(this).children().eq(0);
            if (imgElement.attr("localPath") != undefined && imgElement.attr("localPath") != '') {
                base.detectImage({localPath : imgElement.attr("localPath")});
            } else {
                base.detectImage({netUrl : imgElement.attr("src")});
            }
            $("#mainImg").attr("src", imgElement.attr("src"));
        })
    }

    //列出解析到的人脸
    this.showDetectResult = function (responseJson) {
        var parentDiv = document.getElementById('mainDiv');
        //每次检测清空上次的查询结果
        base.attributes = {};
        var src = $("#mainImg").attr("src");
        var natureImgSize = FileHelper.getNatureImgSize(src);
        var html = "<div id='master' class='col-sm-2 resultImg'>" +
                    "<img src='" + src + "' class= mainImg></div>";
        var str = "已检测到图中 " + responseJson.length + " 张人脸，并生成对应face_token，点击人脸图片查看结果信息。";
        base.attributes["master"] = "<p style='font-size: 20px;width: 90%;" +
            "margin-top: 25%;margin-left: 5%' align='center'>" + str + "</p>";
        for (var i = 0;i < (responseJson.length > 4 ? 4 : responseJson.length); i++) {
            var faceToken = responseJson[i].faceToken;
            base.attributes[faceToken] = base.drawFaceAttributesHtml(responseJson[i].attributes);
            //检测到的人脸位置
            var detectPosition = responseJson[i].faceRectangle;
            //方框框出检测到的人脸
            base.drawFaceOutLine(detectPosition, parentDiv);
            var ratio = 110 / Number(detectPosition.width);
            var width = natureImgSize.width * ratio;
            var left = (0 - (Number(detectPosition.left) * ratio)) + "px";
            var top = (0- (Number(detectPosition.top) * ratio)) + "px";
            html += "<div id='" + responseJson[i].faceToken + "' class='resultImg' " +
                    "style='background:url(" + src + ");background-size: " + width + "px;background-position: " + left + " " + top + ";'>" +
                    "</div>";
        }
        $("#resultImgList").html(html);
        base.bindResultImgListClick();
        base.renderFaceHtml('master');
    }
    
    //切换图片时，清除上一个图片框出的人脸位置
    this.clearFaceOutLine = function () {
        var parentDiv = document.getElementById('mainDiv');
        var OutLines = document.getElementsByClassName('tempOutLine');
        while (OutLines.length > 0) {
            parentDiv.removeChild(OutLines[0]);
        }
    }

    //在主图上用方框框出检测到的人脸
    this.drawFaceOutLine = function (position, parentDiv) {
        var mainImg = $("#mainImg");
        var mainDiv = $("#mainDiv");
        var mainImgWidth = mainImg.width();
        var mainImgHeight = mainImg.height();
        var mainDivWidth = mainDiv.outerWidth();
        var mainDivHeight = mainDiv.outerHeight();
        var natureImg = FileHelper.getNatureImgSize(mainImg.attr("src"));
        var ratio = mainImgWidth / Number(natureImg.width);
        var left = Number(position.left) * ratio + (mainDivWidth - mainImgWidth) / 2;
        var top = Number(position.top) * ratio + (mainDivHeight - mainImgHeight) / 2;
        var width = Number(position.width) * ratio;
        var height = Number(position.height) * ratio;
        var div = document.createElement("div");
        //为div添加样式
        var style = document.createAttribute("style");
        div.className = "tempOutLine";
        div.setAttributeNode(style);
        div.style.width = width + "px";
        div.style.height = height + "px";
        div.style.position = "absolute";
        div.style.left = left + "px";
        div.style.top = top + "px";
        div.style.border = "solid 2px #00b2e0";
        parentDiv.appendChild(div);
    }

    //imglist绑定点击事件
    this.bindResultImgListClick = function () {
        $(".resultImg").click(function () {
            $(".resultImg").each(function () {
                $(this).css("border", "0px");
            });
            $(this).css("border", "solid 2px #00b2e0");
            base.renderFaceHtml($(this).attr('id'));
        })
    }
    
    //根据属性绘制每个人脸html
    this.drawFaceAttributesHtml = function (attributes) {
        if (attributes == undefined) {
            return "";
        }
        var age = attributes.age.value;
        var gender = attributes.gender.value == 'Female' ? '女性' : attributes.gender.value == 'Male' ? '男性' : '未知';
        var ethnicity = attributes.ethnicity.value;
        var beautyScore = attributes.gender.value == 'Female' ? attributes.beauty.femaleScore : attributes.beauty.maleScore;
        var threshold = attributes.smile.threshold;
        var smile = attributes.smile.value;
        var html = "<table class='attribute'>" +
            "<tr><th>年龄</th><td>" + age + "</td></tr>" +
            "<tr><th>性别</th><td>" + gender + "</td></tr>" +
            "<tr><th>人种</th><td>" + ethnicity + "</td></tr>" +
            "<tr><th>微笑程度</th><td>值： " + smile + "；阈值：" + threshold + "</td></tr>" +
            "<tr><th>颜值打分</th><td>" + beautyScore + "</td></tr>" +
            "</table>"
        return html;
    }

    //渲染属性html
    this.renderFaceHtml = function (id) {
        $("#attributes").html(base.attributes[id]);
    }

    //刷新蒙板
    this.refreshMask = function (element) {
        $(".elementDiv").each(function () {
            $(this).addClass("mask");
        })
        if (element != undefined && element != null) {
            element.removeClass("mask");
        }
    }
}

var detectModel = new DetectView.search();
$(function () {
    detectModel.bindFileChange()
    detectModel.initImgList();
    detectModel.bindImgListClick();
    //触发默认第一张图片click事件
    $(".elementDiv").eq(0).trigger("click");
    detectModel.attributes = {};
    $("#myTabContent").css("height", ($("#left").height() - $("#myTab").height()));
    $("#jsonResponse").css("height", ($("#left").height() - $("#myTab").height()));
    $("#NetUrl").keypress(function (e) {
        if (e.keyCode == "13") {
            detectModel.detectNetImage();
        }
    })
})