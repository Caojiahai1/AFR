var DetectView = {};

DetectView.search = function (params) {
    var base = this;

    var detectImage = function (result) {
        if (result.success) {
            localPath = result.object;
            base.detectLocalImage(localPath);
        } else {
            alert(result.message);
        }
    }
    
    this.detectLocalImage = function (localPath) {
        var data = {
            localPath : localPath
        }
        $.ajax({
            url: "/FaceDetect/detectLocalImage?random=" + Math.random(),
            type: "json",
            method: "post",
            data: data,
            dataType: 'json',
            success: function (data) {
                console.log(data)
                $("#jsonResponse").html(formatJson(JSON.stringify(data.object)));
            }
        });
    }
    
    this.detectNetImage = function () {
        
    }

    this.fileUpload = function () {
        var result = FileHelper.imageUpload("lefile", detectImage);
    }
    
    this.bindFileChange = function () {
        $("#lefile").change(function () {
            model.fileUpload();
            $("#mainImg").attr("src", FileHelper.getObjectURL(this.files[0]));
            // change事件触发一次后失效bug
            base.bindFileChange();
        })
    }


}

var model = new DetectView.search();
$(function () {
    model.bindFileChange()
})