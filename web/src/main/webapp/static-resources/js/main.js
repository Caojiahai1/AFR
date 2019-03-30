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

$(function () {
    mainWindow.tabChange();
    mainWindow.headerClick();
})