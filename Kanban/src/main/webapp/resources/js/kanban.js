/** 칸반 보드 전용 */
/* $(function() {
	 $(".add_work").click
});*/

window.addEventListener("DOMContentLoaded",function() {
	const addWork = document.querySelector(".add_work");
	addWork.addEventListener("click",function() {
		layer.popup("../kanban/add",500,600);		
	},false);	
}, false);