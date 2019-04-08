var mainWindow = {};

// 导航栏选中事件
mainWindow.tabChange = function () {
    $(".navbar-nav a").slice(0,4).click(function () {
        var element = $(".navbar-nav a");
        element.each(function () {
            $(this).parent().removeClass("active");
        });
        $(this).parent().addClass("active");
        mainWindow.changeIframe($(this).attr("url"));
    });
}

mainWindow.changeIframe = function (url) {
    $('iframe').attr("src", url);
}

mainWindow.headerClick = function () {
    $(".navbar-header a").click(function () {
        var element = $(".navbar-nav a");
        element.each(function () {
            $(this).parent().removeClass("active");
        });
        mainWindow.changeIframe("/index.jsp");
    })
}

mainWindow.alert = function (type, message) {
    if (type == undefined || type == '') {
        return;
    }
    if (type == 'success') {
        $("#myModalLabel").text("成功！");
        $("#myModalBody").text(message);
        $("#myModalLabel").css("color", "#3c763d");
        $("#myModalBody").css("color", "#3c763d");
        $("#modalButton").removeClass("btn-danger btn-warning");
        $("#modalButton").addClass("btn-success");
    } else if (type == 'fail') {
        $("#myModalLabel").text("失败！");
        $("#myModalBody").text(message);
        $("#myModalLabel").css("color", "#a94442");
        $("#myModalBody").css("color", "#a94442");
        $("#modalButton").removeClass("btn-success btn-warning");
        $("#modalButton").addClass("btn-danger");
    } else if (type == 'warn') {
        $("#myModalLabel").text("警告！");
        $("#myModalBody").text(message);
        $("#myModalLabel").css("color", "#8a6d3b");
        $("#myModalBody").css("color", "#8a6d3b");
        $("#modalButton").removeClass("btn-success btn-danger");
        $("#modalButton").addClass("btn-warning");
    }
    $("#alertModal").modal();
}

$(function () {
    mainWindow.tabChange();
    mainWindow.headerClick();
})