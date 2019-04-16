var CompareView = {};
CompareView.search = function () {
    var base = this;
    //保存第一张图人脸信息
    this.faceInfo1 = {};
    //保存第二张图人脸信息
    this.faceInfo2 = {};
    //当前操作的图片（0:无 1:第一张图片 2:第二张图片）
    this.imageNum = 0;
    //第一张图faceToken
    this.faceToken1 = "";
    //第二张图faceToken
    this.faceToken2 = "";

    //静态数据
    this.staticInfo = {
        1 : {
            mainDivId : "imagediv1",
            mainImgId : "image1",
            tempClassName : "tempOutLine1",
            resultClass : "resultImg1",
            imageResultId : "image1Result"
        },
        2 : {
            mainDivId : "imagediv2",
            mainImgId : "image2",
            tempClassName : "tempOutLine2",
            resultClass : "resultImg2",
            imageResultId : "image2Result"
        }
    }

    //清空参数
    this.clear = function () {
        base.faceInfo1 = {};
        base.faceInfo2 = {};
        base.imageNum = 0;
        base.faceToken1 = "";
        base.faceToken2 = "";
    }

    //检查是否选中上传图片的位置
    this.uploadImagePositionCheck = function (currentImgNum) {
        if (currentImgNum == undefined || currentImgNum == 0) {
            mainWindow.alert("warn", "请选择图片上传的位置！");
            return false;
        }
        return true;
    }

    //绑定主图选中事件
    this.bindMainImgClick = function () {
        $(".compareDiv").click(function () {
            $(".compareDiv").each(function () {
                $(this).removeClass("compareDivBorder");
            })
            $(this).addClass("compareDivBorder");
            base.imageNum = $(this).attr("imageNum");
        })
    }

    //上传图片
    this.fileUpload = function (currentImgSrc, currentImgNum) {
        if (!base.uploadImagePositionCheck(currentImgNum)) {
            return;
        }
        base.clearLastDetectResult(currentImgNum);
        $("#" + base.staticInfo[currentImgNum].mainImgId).attr("src", currentImgSrc);
        base.clearFaceOutLine(base.staticInfo[currentImgNum].mainDivId, base.staticInfo[currentImgNum].tempClassName);
        var data = {detectFace : true};
        var json = JSON.stringify(data);
        var param = {
            currentImgSrc : currentImgSrc,
            currentImgNum : currentImgNum
        }
        FileHelper.imageUpload("lefile", {json : json}, uploadImageCallback, param);
    }

    //上传图片回调函数
    var uploadImageCallback = function (result, param) {
        if (result.success) {
            if (result.object) {
                var currentImgSrc = param.currentImgSrc;
                var currentImgNum = param.currentImgNum;
                $("#" + base.staticInfo[currentImgNum].mainImgId).attr("localPath", result.object.localPath);
                base.showDetectFaces(result.object.json, currentImgSrc, currentImgNum);
            }
        } else {
            mainWindow.alert("fail", result.message);
        }
    }

    //绑定上传图片事件
    this.bindFileChange = function () {
        $("#lefile").change(function () {
            if (this.files[0] != undefined) {
                var src = FileHelper.getObjectURL(this.files[0]);
                base.fileUpload(src, base.imageNum);
            }
            // change事件触发一次后失效bug
            base.bindFileChange();
        })
    }
    
    //列出检测到的人脸
    this.showDetectFaces = function (responseJson, currentImgSrc, currentImgNum) {
        if (!base.uploadImagePositionCheck(currentImgNum)) {
            return;
        }
        var natureImgSize = FileHelper.getNatureImgSize(currentImgSrc);
        var parentDiv = null;
        var faceInfoTemp = {};
        if (currentImgNum == 1) {
            base.faceInfo1 = faceInfoTemp;
        } else if (currentImgNum == 2) {
            base.faceInfo2 = faceInfoTemp;
        }
        parentDiv = $("#" + base.staticInfo[currentImgNum].imageResultId);
        var resultClass = base.staticInfo[currentImgNum].resultClass;
        var html = '';
        for (var i = 0;i < (responseJson.length > 4 ? 4 : responseJson.length); i++) {
            var faceToken = responseJson[i].faceToken;
            // base.attributes[faceToken] = base.drawFaceAttributesHtml(responseJson[i].attributes);
            //检测到的人脸位置
            var detectPosition = responseJson[i].faceRectangle;
            faceInfoTemp[faceToken] = detectPosition;
            var divWith = parentDiv.outerWidth() * 0.44;
            var ratio = divWith / Number(detectPosition.width);
            var width = natureImgSize.width * ratio;
            var left = (0 - (Number(detectPosition.left) * ratio)) + "px";
            var top = (0- (Number(detectPosition.top) * ratio)) + "px";
            html += "<div id='" + responseJson[i].faceToken + "' imageNum = " + currentImgNum + " class='" + resultClass + "' " +
                "style='background:url(" + currentImgSrc + ");background-size: " + width + "px;background-position: " + left + " " + top + ";'>" +
                "</div>";
        }
        parentDiv.html(html);
        base.bindEachFaceClick(resultClass);
    }

    //绑定人脸结果集点击事件
    this.bindEachFaceClick = function (resultClass) {
        $("." + resultClass).click(function () {
            $("." + resultClass).each(function () {
                $(this).css("border", "0px");
            });
            $(this).css("border", "solid 2px #00b2e0");
            base.drawFaceOutLine($(this).attr('id'), $(this).attr("imageNum"));
        })
    }

    //在主图上用方框框出检测到的人脸
    this.drawFaceOutLine = function (facetoken, imageNum) {
        var position;
        if (imageNum == 1) {
            position = base.faceInfo1[facetoken];
            base.faceToken1 = facetoken;
        } else if (imageNum == 2) {
            position = base.faceInfo2[facetoken];
            base.faceToken2 = facetoken;
        } else {
            mainWindow.alert("warn", "当前人脸未绑定图片！");
            return;
        }
        var imgDivId = base.staticInfo[imageNum].mainDivId;
        var imgId= base.staticInfo[imageNum].mainImgId;
        var tempClassName = base.staticInfo[imageNum].tempClassName;
        //清空上一个框框
        base.clearFaceOutLine(imgDivId, tempClassName);
        var mainImg = $("#" + imgId);
        var mainDiv = $("#" + imgDivId);
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
        div.className = tempClassName;
        div.setAttributeNode(style);
        div.style.width = width + "px";
        div.style.height = height + "px";
        div.style.position = "absolute";
        div.style.left = left + "px";
        div.style.top = top + "px";
        div.style.border = "solid 2px #00b2e0";
        var parentDiv = document.getElementById(imgDivId);
        parentDiv.appendChild(div);
    }

    //切换图片时，清除上一个框出的人脸位置
    this.clearFaceOutLine = function (parentId, tempClassName) {
        var parentDiv = document.getElementById(parentId);
        var OutLines = document.getElementsByClassName(tempClassName);
        while (OutLines.length > 0) {
            parentDiv.removeChild(OutLines[0]);
        }
    }
    
    //新上传图片时，清除上一张图片的检测结果
    this.clearLastDetectResult = function (currentImgNum) {
        $("#" + base.staticInfo[currentImgNum].imageResultId).html("");
    }

    //检索网络url
    this.detectNetImage = function () {
        var netUrl = $("#compareNetUrl").val();
        if (!FileHelper.checkNetURL(netUrl)) {
            mainWindow.alert("warn", "请输入正确的图片路径！");
            $("#compareNetUrl").val("");
            return;
        }
        var currentImgNum = base.imageNum;
        if (!base.uploadImagePositionCheck(currentImgNum)) {
            return;
        }
        base.detectImage(netUrl, currentImgNum);
        base.clearLastDetectResult(currentImgNum);
        base.clearFaceOutLine(base.staticInfo[currentImgNum].mainDivId, base.staticInfo[currentImgNum].tempClassName);
        $("#" + base.staticInfo[currentImgNum].mainImgId).attr("src", netUrl);
        $("#" + base.staticInfo[currentImgNum].mainImgId).attr("localPath", "");
    }

    //ajax请求后台检测接口
    this.detectImage = function (currentImgSrc, currentImgNum) {
        $.ajax({
            url: "/DetectFace/detectImage?random=" + Math.random(),
            type: "json",
            method: "post",
            data: {netUrl : currentImgSrc},
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    base.showDetectFaces(data.object, currentImgSrc, currentImgNum);
                } else {
                    mainWindow.alert("fail", data.message);
                }
            },
            beforeSend: function () {
                myTools.circleLoading(base.staticInfo[currentImgNum].mainDivId);
            },
            complete: function () {
                myTools.circleLoadingOver(base.staticInfo[currentImgNum].mainDivId);
            }
        });
    }
    
    //比较完成后，重新在图上框出人脸位置
    this.drawComparedFaceOutline = function () {
        
    }

    //根据返回比较的值，分析是否同一个人
    this.analysisResults = function (confidence, e3, e4, e5) {
        if (confidence < e3) {
            return "不是同一个人";
        }
        if (confidence > e3 && confidence < e4) {
            if (confidence < 80) {
                return "大概率不是同一个人";
            } else {
                return "可能是同一个人";
            }
        }
        if (confidence > e4 && confidence < e5) {
            if (confidence < 80) {
                return "可能不是同一个人";
            } else {
                return "大概率是同一个人";
            }
        }
        if (confidence > e5) {
            if (confidence > 80) {
                return "是同一个人";
            } else {
                return "大概率是同一个人";
            }
        }
    }
    
    //进行比较
    this.doCompare = function () {
        var param = {};
        //第一张图片参数
        if (base.faceToken1 == undefined || base.faceToken1 == '') {
            var img1 = $("#" + base.staticInfo[1].mainImgId);
            if (img1.attr("localPath") != undefined && img1.attr("localPath") != '') {
                param.localPath1 = img1.attr("localPath");
            } else {
                param.netUrl1 = img1.attr("src");
            }
        } else {
            param.faceToken1 = base.faceToken1;
        }
        //第二张图片参数
        if (base.faceToken2 == undefined || base.faceToken2 == '') {
            var img2 = $("#" + base.staticInfo[2].mainImgId);
            if (img2.attr("localPath") != undefined && img2.attr("localPath") != '') {
                param.localPath2 = img2.attr("localPath");
            } else {
                param.netUrl2 = img2.attr("src");
            }
        } else {
            param.faceToken2 = base.faceToken2;
        }
        $.ajax({
            url: "/CompareFace/doCompare?random=" + Math.random(),
            type: "json",
            method: "post",
            data: param,
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    delete data.object.requestId;
                    var json = data.object;
                    base.showResponseJson(json);
                    var thresholds = json.thresholds;
                    $("#analysisResult").text("解析结果:" + base.analysisResults(Number(json.confidence), Number(thresholds.e3), Number(thresholds.e4), Number(thresholds.e5)));
                } else {
                    mainWindow.alert("fail", data.message);
                }
            },
            beforeSend: function () {
                myTools.loading();
            },
            complete: function () {
                myTools.loadingOver();
            }
        });
    }

    //展示返回json
    this.showResponseJson = function (json) {
        $("#jsonResponse").html(formatJson(JSON.stringify(json)), 2);
    }
}

var compareModel = new CompareView.search();
$(function () {
    compareModel.bindFileChange();
    compareModel.bindMainImgClick();
    compareModel.clear();
    //触发默认图片检测
    $(".compareDiv").eq(0).trigger("click");
    compareModel.detectImage($("#" + compareModel.staticInfo[1].mainImgId).attr("src"), 1);
    compareModel.detectImage($("#" + compareModel.staticInfo[2].mainImgId).attr("src"), 2);
    $("#myTabContent").css("height", ($("#left").height() - $("#myTab").height()));
    $("#jsonResponse").css("height", ($("#left").height() - $("#myTab").height()));
    $("#compareNetUrl").keypress(function (e) {
        if (e.keyCode == "13") {
            compareModel.detectNetImage();
        }
    })
})