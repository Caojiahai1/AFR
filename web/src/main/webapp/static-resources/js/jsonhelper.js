var formatJson = function (v, options) {
    v = v.replace(/&quot;/g, '"');
    v = v.replace(/ /g, '');
    var reg = /(\[|{|,)/g;
    v = v.replace(reg, function (a, b) {
        return a + "<br/>";
    });
    reg = /(}|\])/g;
    v = v.replace(reg, function (a, b) {
        return "<br/>" + a;
    });
    reg = /(:\[)/g;
    v = v.replace(reg, function (a, b) {
        return a[0] + "<br/>" + a[1];
    });

    var arr = v.toString().split("<br/>")

    var arr1 = new Array("[", "]");
    var arr2 = new Array("{", "}");
    var pre = "";
    v = "";
    var chr = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    var lth = chr.length;
    var prev = "";
    $.each(arr, function (index, item) {
        if (item == "[" || item == "{") {
            if (pre != "},") {
                prev += chr;
            }
            v += prev + item + "<br />";
        } else if (item == "]" || item == "}" || item == "},") {
            prev = prev.substr(0, prev.length - lth);
            v += prev + item + "<br />";
        } else {
            if (pre.indexOf("{") != -1) {
                prev += chr;
            }
            v += prev + item + "<br />";
        }
        pre = item;
    });
    // var regarr = [/包裹信息/g,/运单号/g,/商品信息/g,/重量/g,/规格代码/g,/数量/g];
    // for (var i = 0; i < regarr.length; i++) {
    //     v = v.replace(regarr[i], function (a, b) {
    //         return "<span style=\"color:#CC0000;\">" + a + "</span>";
    //     });
    // }

    var reg = /((:\s*\'([^\"]*)\'(,?))|(:\s*(\d+(\.\d)*|null)+[,]?))/g;
    v = v.replace(reg, function (a, b) {
        return "<span style=\"color:#007777;\">" + a + "</span>";
    });
    return v;
};