
const layer = {
	
	// 레이어 팝업 열기
	popup : function(url, width, height) {
		
		if (!url)
			return;
		
		width = width || 350;
		height = height || 350;
		
		if ($("#layer_dim").length == 0) {
			$("body").append("<div id='layer_dim'></div>");			
		}
		
		$("#layer_dim").css({
			position: "fixed",
			width : "100%",
			height : "100%",
			background : "rgba(0, 0, 0, 0.6)",
			zIndex : 100,
			top: 0,
			left: 0,
			cursor : "pointer",
		});
		
		// 레이어 팝업
		if ($("#layer_popup").length == 0) {
			$("body").append("<div id='layer_popup'></div>");	
		}
		
		const xpos = Math.round(($(window).width() - width) / 2);
		const ypos = Math.round(($(window).height() - height) / 2);	
			
		$("#layer_popup").css({
			position: "fixed",
			width : width + "px",
			height : height + "px",
			background : "#ffffff",
			zIndex : 101,
			top : ypos + "px",
			left : xpos + "px",
			borderRadius : "20px",
			padding: "20px",
		});
		
		const popupHtml = `<iframe src='${url}' width='${width}' height='${height}' frameborder='0' scrolling='auto'></iframe>`;
		$("#layer_popup").html(popupHtml);
	},

	//레이어 팝업 닫기
	close : function() {
		$("#layer_popup, #layer_dim").remove();
	}
};

$(function() { 
	$("body").on("click", "#layer_dim", function() {
		layer.close();
	});
});
