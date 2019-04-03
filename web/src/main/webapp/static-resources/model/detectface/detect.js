var DetectView = {};

DetectView.search = function (params) {
    var base = this;

    this.imgList = {};

    //想图片列表插入新检测的图片
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

    this.detectNetImage = function () {
        var netUrl = $("#NetUrl").val();
        $.ajax({
            url: "/FaceDetect/detectImage?random=" + Math.random(),
            type: "json",
            method: "post",
            data: {netUrl : netUrl},
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    $("#jsonResponse").html(formatJson(JSON.stringify(data.object)));
                } else {
                    alert(data.message);
                }
            }
        });
        $("#mainImg").attr("src", netUrl);
        base.addImg(netUrl, "");
    }


    //上传图片
    this.fileUpload = function () {
        var data = {detectFace : true};
        var json = JSON.stringify(data);
        var result = FileHelper.imageUpload("lefile", {json : json}, detectImage);
    }

    //上传图片回调函数
    var detectImage = function (result) {
        if (result.success) {
            // localPath = result.object;
            // base.detectLocalImage(localPath);
            if (result.object) {
                base.addImg($("#mainImg").attr("src"), result.object.localPath);
                $("#jsonResponse").html(formatJson(JSON.stringify(result.object.json)));
            }
        } else {
            alert(result.message);
        }
    }
    
    this.bindFileChange = function () {
        $("#lefile").change(function () {
            if (this.files[0] != undefined) {
                model.fileUpload();
                $("#mainImg").attr("src", FileHelper.getObjectURL(this.files[0]));
            }
            // change事件触发一次后失效bug
            base.bindFileChange();
        })
    }

}

var model = new DetectView.search();
$(function () {
    model.bindFileChange()
    model.initImgList();
})