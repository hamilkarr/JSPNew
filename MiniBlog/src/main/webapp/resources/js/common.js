let page = 2;
$(function() {
	$("#button").click(function() {
		$.ajax({
			url : "../blog",
			type : "get",
			data : { page : page, isAjax : 1 },
			dataType : "html",
			success : function (res) {
				if (res.trim() == "") {
					$("#button").remove();
				} else {
					$("#blog_post").append(res);
					page++;
				}
			},
			error : function(err) {
				console.error(err);
			}
		});	
	});
});

