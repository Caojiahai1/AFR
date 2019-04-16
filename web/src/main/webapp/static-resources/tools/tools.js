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
    document.body.classList.add("spinnermask");
    document.body.style.userSelect = "none";
    document.body.style.pointerEvents = "none";
}

//加载结束
myTools.loadingOver = function () {
    var spinner = document.getElementsByClassName("spinner");
    while (spinner.length > 0) {
        document.body.removeChild(spinner[0]);
    }
    document.body.classList.remove("spinnermask");
    document.body.style.userSelect = "text";
    document.body.style.pointerEvents = "auto";
}

//圈圈加载
myTools.circleLoading = function (parentId) {
    var parent;
    if (parentId != undefined && parentId != '') {
        parent = document.getElementById(parentId);
    } else {
        parent = document.body;
    }
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
    parent.appendChild(spinner);
    parent.classList.add("spinnermask");
    document.body.style.userSelect = "none";
    document.body.style.pointerEvents = "none";
}

//圈圈加载结束
myTools.circleLoadingOver = function (parentId) {
    var parent;
    if (parentId != undefined && parentId != '') {
        parent = document.getElementById(parentId);
    } else {
        parent = document.body;
    }
    var childList = parent.childNodes;
    for (var i = 0; i < childList.length; i++) {
        if (childList[i].className == "spinner2") {
            parent.removeChild(childList[i]);
            break;
        }
    }
    parent.classList.remove("spinnermask");
    document.body.style.userSelect = "text";
    document.body.style.pointerEvents = "auto";

}