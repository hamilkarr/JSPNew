let page = 2;
$(function() {
	$("#button").click(function() {
		$.ajax({
			url : "../blog/list",
			type : "GET",
			data : { page : page, isAjax : 1 },
			dataType : "html",
			success : function (res) {
				const st = $(".post_list tbody").height();
				if (res.trim() == "") {
					$("#button").remove();
				} else {
					$("#blog_content").append(res);
					page++;
					
					$("body, html").animate({scrollTop : st + "px"}, 1000);
				}
			},
			error : function(err) {
				console.error(err);
			}
		});
	});
});