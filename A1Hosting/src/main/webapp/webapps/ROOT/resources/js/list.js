// 전체, 일반, 팁과 정보 사이트 이동시 클래스 추가

$(document).ready(function() {
    var url = location.href;
    // var getAr0 = url.indexOf("list");
    var getAr1 = url.indexOf("normal");
    var getAr2 = url.indexOf("tip");

    // if(getAr0 != -1) {
    //     $("#list").addClass("on");
    // }
    if(getAr1 != -1) {
        $("#normal").addClass("on");
        $("#total").removeClass("on");
    }
    if(getAr2 != -1) {
        $("#tip").addClass("on");
        $("#total").removeClass("on");
    }
});	