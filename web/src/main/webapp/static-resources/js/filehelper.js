var FileHelper = {};

FileHelper.imageUpload = function (fileElementId, data, callback) {
    $.ajaxFileUpload
    (
        {
            url: '/image/upload', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileElementId,
            data: data,
            dataType: 'json', //返回值类型 一般设置为json
            contentType : 'application/json;charset=utf-8',
            success: function (result, status)  //服务器成功响应处理函数
            {
                callback(result);
            },
            error: function (result, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
}

FileHelper.getObjectURL = function (file) {
    var url = null ;
    // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
    if (window.createObjectURL!=undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}