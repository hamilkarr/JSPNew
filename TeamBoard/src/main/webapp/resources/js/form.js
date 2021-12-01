$(function(){
	CKEDITOR.replace("content");
	CKEDITOR.config.height = 300;
	
	$(".addImage").click(function(){
		let url = "../popup/upload";
		$el = $("input[name='gid']");
		if ($el.length > 0) {
			url += "?gid=" + $el.val();
		}
		
		layer.popup(url, 350, 350);
	});
});

$(function(){
	$("input[name='file']").change(function(){
		frm.submit();
	});
	
	/** 첨부 파일 삭제 처리  */
	$("body").on("click", "#attached_files .remove", function() {
		if (!confirm('정말 삭제하시겠습니까?')) {
			return;
		}
		const fileBox = $(this).closest(".file_box");
		const idx = fileBox.data("idx");
		
		$.ajax({
			url : "../file",
			type : "post",
			dataType : "text",
			data : { mode : "delete", idx : idx },
			success : function(res) {
				if (res.trim() == 1) { // 삭제 성공 
					fileBox.remove();
				} else { // 삭제 실패 
					alert("파일 삭제 실패하였습니다.");
				}
			},
			error : function(err) {
				console.log(err);
			}
		});
	});
	
	/** 이미지 본문 추가  */
	$("body").on("click", "#attached_files .insert_editor", function() {
		const url = $(this).closest(".file_box").data("url");
		let html = `<img src='${url}'>`;
		CKEDITOR.instances.content.insertHtml(html);
	});
});

function callbackUploadImages(images){
	if(!images){
		return;
	}
	
	images = JSON.parse(images);
	
	const template = $("#template_file_box").html();
	
	let html = "";
	for (let key in images) {
		const image = images[key];
		html += `<img src='${image.uploadURL}'>`;
		
		const downloadURL = "../file?mode=download&idx=" + image.idx;
		const count = $("#attached_files li").length + 1;
		let fileBox = template.replace(/#idx/g, image.idx)
									.replace(/#url/g, image.uploadURL)
									.replace(/#fileName/g, image.fileName)
									.replace(/#downloadURL/g, downloadURL)
									.replace(/#count/g, count);
	  	$("#attached_files").append(fileBox);			
	}
	CKEDITOR.instances.content.insertHtml(html);
	layer.close();
}