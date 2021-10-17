/**
* 레이어 팝업 
*
 */
const layer = {
	/**
		레이어 팝업열기  
	*/
	popup: function(url, width, height) {

		// URL이 없으면 팝업 처리 중단
		if (!url)
			return;

		width = width || 350;
		height = height || 350;

		/** 레이어 팝업 백그라운드  */
		if ($("#layer_dim").length == 0) { 
			$("body").append("<div id='layer_dim'></div>");
		}

		/** 레이어 팝업  */
		if ($("#layer_popup").length == 0) { // layer_popup이 없으면 추가 
			$("body").append("<div id='layer_popup'></div>");
		}

		const xpos = Math.round(($(window).width() - width) / 2);
		const ypos = Math.round(($(window).height() - height) / 2);

		$("#layer_popup").css({
			position: "fixed",
			width: width + "px",
			height: height + "px",
			background: "#ffffff",
			zIndex: 101,
			top: ypos + "px",
			left: xpos + "px",
			borderRadius: "20px",
			padding: "20px",
		});

		const popupHtml = `<iframe src='${url}' width='${width}' height='${height}' frameborder='0' scrolling='auto'></iframe>`;
		$("#layer_popup").html(popupHtml);
	},
	/**
		레이어 팝업 닫기
	 */
	close: function() {
		$("#layer_popup, #layer_dim").remove();
	}
};

$(function() {
	/** 레이어 팝업 닫기 처리  */
	$("body").on("click", "#layer_dim", function() {
		layer.close();
	});
});