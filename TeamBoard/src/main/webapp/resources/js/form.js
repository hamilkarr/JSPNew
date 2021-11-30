$(function(){
	CKEDITOR.replace("content");
	CKEDITOR.config.height = 300;
	
	$(".addImage").click(function(){
		layer.popup("../popup/upload", 350, 350);
	});
});

$(function(){
	$("input[name='file']").change(function(){
			frm.submit();
	});
});

function callbackUploadImages(images){
	if(!images){
		return;
	}
	
	images = images.split("||");
	let html = "";
	images.forEach(function(image){
		html += `<img src='${image}'>`;
	});
	
	CKEDITOR.instances.content.insertHtml(html);
	
	layer.close();
}