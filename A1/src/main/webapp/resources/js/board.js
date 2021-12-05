$(function() {
	/** 
	* 게시판 목록 회원 아이디 클릭시 회원 프로필 정보 보기  
	*
	*/
	$(".profile_popup").click(function() {
		const memLv= $(this).data("memLv");
		/** 관리자는 팝업 미 노출 */
		if (memLv == 'admin') 
			return;
		
		const memId = $(this).data("memId");
		const offset = $(this).offset();
		$.ajax({
			url : "../profile",
			type : "get",
			dataType : "json",
			data : { memId : memId, outline : "none" },
			success : function(res) {
				if (res.message) {
					alert(message);
				}
				// 프로필 조회 성공시 템플릿 완성 
				if (res.success && res.profile) {
					const profile = res.profile;
					let html = $("#tpl_profile_popup").html();
					html = html.replace(/#memLv/g, profile.memLv)
							.replace(/#memNm/g, profile.memNm)
							.replace(/#memId/g, profile.memId);
							
					// 기존 팝업을 제거하고 다시 생성 
					$("#popup_profile").remove();
					$("body").append(html);
					
					$("#popup_profile").css({
						top : offset.top + "px",
						left : offset.left + "px", 
					})
				}
			},
			error : function(err) {
				console.log(err);	
			}
		});
	});
	
	/** 프로필 팝업 닫기  */
	$("body").on("click", "#popup_profile .remove", function() {
		$("#popup_profile").remove();
	});
	
	$("body").on("mouseleave", "#popup_profile", function() {
		
		$("#popup_profile").fadeOut(1000);
		setTimeout(function() {
			$("#popup_profile").remove();
		}, 1000);
	});
});