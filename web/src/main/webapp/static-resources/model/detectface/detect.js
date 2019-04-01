var DetectView = {};

DetectView.search = function (params) {
    var base = this;

    this.fileUpload = function () {
        $.ajaxFileUpload
        (
            {
                url: '/image/upload', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: "lefile",
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    $("#img1").attr("src", data.imgurl);
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                            alert(data.error);
                        } else {
                            alert(data.msg);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
    }
}

var model = new DetectView.search();
$(function () {
    // model.fileChange("lefile");
})