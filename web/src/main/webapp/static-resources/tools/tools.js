var myTools = {};
//加载中
myTools.loading = function () {
    var spinner = document.createElement("div");
    spinner.className = "spinner";
    for (var i = 1; i < 7; i++) {
        var rect = document.createElement("div");
        rect.className = "rect" + i;
        spinner.appendChild(rect);
    }
    document.body.appendChild(spinner);
    document.body.style.userSelect = "none";
    document.body.style.pointerEvents = "none";
}

//加载结束
myTools.loadingOver = function () {
    var spinner = document.getElementsByClassName("spinner");
    while (spinner.length > 0) {
        document.body.removeChild(spinner[0]);
    }
    document.body.style.userSelect = "text";
    document.body.style.pointerEvents = "auto";
}

//圈圈加载
myTools.circleLoading = function (parentId) {
    var spinner = document.createElement("div");
    spinner.className = "spinner2";
    for (var i = 1; i < 4; i++) {
        var container = document.createElement("div");
        container.className = "spinner2-container container" + i;
        for (var j = 1; j < 5; j++) {
            var circle = document.createElement("div");
            circle.className = "circle" + j;
            container.appendChild(circle);
        }
        spinner.appendChild(container);
    }
    document.getElementById(parentId).appendChild(spinner);
    document.body.style.userSelect = "none";
    document.body.style.pointerEvents = "none";
}

//圈圈加载结束
myTools.circleLoadingOver = function (parentId) {
    var childList = document.getElementById(parentId).childNodes;
    for (var i = 0; i < childList.length; i++) {
        if (childList[i].className == "spinner2") {
            document.getElementById(parentId).removeChild(childList[i]);
            break;
        }
    }
    document.body.style.userSelect = "text";
    document.body.style.pointerEvents = "auto";

}