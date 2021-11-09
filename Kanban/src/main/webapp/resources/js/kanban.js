/**
* 칸반보드 전용 
*
*/
window.addEventListener("DOMContentLoaded", function() {
	/* 작업 등록 */
	const addWork = document.querySelector(".add_work");
	if (addWork) {
		addWork.addEventListener("click", function() {
			layer.popup("../kanban/add", 500, 600, callbackAddPopup);
		}, false);
	}

	const showWorkList = document.querySelectorAll(".show_work");
	if (showWorkList.length > 0) {
		showWorkList.forEach(function(showWork) {
			showWork.addEventListener("click", function(e) {
				const el = e.target;
				//console.log(el.dataset);
				const idx = el.dataset.idx;
				const url = "../kanban/view?idx=" + idx;
				layer.popup(url, 500, 600, callbackWorkView);
			});
		});
	}
}, false);

function callbackAddPopup() {
	const addFile = document.querySelector(".add_file");
	if (addFile) {
		addFile.addEventListener("click", function() {
			addFileForm();
		}, false);
	}

	const delFile = document.querySelector(".del_file");
	if (delFile) {
		delFile.addEventListener("click", function() {
			delFileForm();
		}, false);
	}
}

/** 파일 첨부 추가 */
function addFileForm() {
	const fileUpload = document.getElementById("file_upload");
	if (fileUpload) {
		const file = fileUpload.firstElementChild;
		const addRows = file.cloneNode(true);
		const cnt = fileUpload.childElementCount + 1;
		addRows.firstElementChild.name = "file" + cnt;

		fileUpload.appendChild(addRows);
	}
}

/** 파일 첨부 삭제 */
function delFileForm() {
	const fileUpload = document.getElementById("file_upload");
	if (fileUpload) {
		if (fileUpload.childElementCount > 1) {
			const lastRows = fileUpload.lastElementChild;
			fileUpload.removeChild(lastRows);
		}
	}
}

/** 작업상세 콜백*/
function callbackWorkView() {
	const deleteFiles = document.querySelectorAll(".attach_files .delete_files");
	deleteFiles.forEach(function(el) {
		el.addEventListener("click", function(e) {
			if (!confirm('정말삭제하겠습니까?')) { return; }
			const target = e.target.parentElement;
			const idx = target.dataset.idx;
			axios({
				url: "../file/delete/" + idx,
				method: "GET",
			}).then(function(res) {
				if (res.data == "1") {
					target.parentElement.removeChild(target);
				} else {
					alert("파일 삭제 실패하였습니다.");
				}

			}).catch(function(err) {
				console.error(err);
			});
		}, false);
	})

	/** 작업 수정 */
	const updateWork = document.querySelector(".update_work");
	if (updateWork) {
		updateWork.addEventListener("click", function(e) {
			const idx = e.target.dataset.idx;
			const url = "../kanban/edit?idx=" + idx;
			layer.popup(url, 500, 600, callbackAddPopup);
		}, false)
	}
}

