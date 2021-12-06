/**
 * 댓글 처리 관련
 */
 const comment = {
	/** URL에 commentNm 번호로 댓글 위치로 이동 */
	moveToComment : function() {
		if (location.search.indexOf("commentNm") == -1) 
			return;	
		
		const pattern = /commentNm=([\d]+)/;
		const match = pattern.exec(location.search);
		if (match[1]) {
			const commentNm = match[1];
			const commentEl = document.getElementById("comment_" + commentNm);
			if (!commentEl)
				return;
				
			// getBoundingClientRect() -> width, height
			const top = commentEl.offsetTop - 100;
			const left = commentEl.offsetLeft;
		
			window.history.scrollRestoration = "manual";
			window.scrollTo(left, top);
		}
	},
	/** 수정 양식 로드 */
	loadEditForm : function(e) {
		const target = e.target;
		const commentNm = target.dataset.commentnm;		
		const xhr = new XMLHttpRequest();
		const url = "../board/comment?mode=get_comment&commentNm=" + commentNm + "&outline=none";
		const id = "comment_form_" + commentNm;
		if (document.getElementById(id)) 
			return;
		
		const loadFormButton = document.querySelector(`#comment_${commentNm} .edit_comment`);	
		xhr.open("GET", url);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				const div = document.createElement("div");
				const editBtns = document.createElement("div");
				const textarea = document.createElement("textarea");
				const editButton = document.createElement("button");
				const cancelButton = document.createElement("button");
				const editText = document.createTextNode("수정");
				const cancelText = document.createTextNode('취소');
				
				div.id = id;
				//css 적용
				div.style.marginTop = "10px";

				textarea.value = xhr.responseText;
				textarea.className = "edit_text";

				editButton.className = "edit";
				cancelButton.className = "cancel";
				editBtns.className = "edit_btns";
				
				editButton.appendChild(editText);
				cancelButton.appendChild(cancelText);
				
				div.appendChild(textarea);
				div.append(editButton);
				div.append(cancelButton);
				div.append(editBtns);
				editBtns.appendChild(editButton);
				editBtns.appendChild(cancelButton);
				const el = document.querySelector(`#comment_${commentNm} .right_cmt`);
				if (el) {
					el.appendChild(div);
					
					// 양식 로딩 버튼(수정버튼) 감추기
					if (loadFormButton && !loadFormButton.classList.contains("hidden")) {
						loadFormButton.classList.add("hidden");
					}
				}
				
				/** 취소 버튼 클릭시 이벤트 */
				cancelButton.addEventListener("click", function() {
					const el = document.getElementById(id);
					const parentEl = el.parentElement;
					parentEl.removeChild(el);
					
					/** 양식 로딩 버튼(수정 버튼) 노출 */
					if (loadFormButton && loadFormButton.classList.contains("hidden")) {
						loadFormButton.classList.remove("hidden");
					}
					
					
				});
				
				/** 수정 버튼 클릭시 이벤트 */
				editButton.addEventListener("click", function() {
					comment.updateComment(commentNm);
				});
			}	
		};
		xhr.send(null);
	},
	/**
	* 댓글 수정 처리 
	* 
	*/
	updateComment : function(commentNm) {
		if (!commentNm) 
			return;
			
		const el = document.querySelector(`#comment_${commentNm} textarea`);
		const content = el.value;
		if (!content || content.trim() == "") {
			alert("수정할 댓글을 입력하세요.");
			return;
		}
		
		const xhr = new XMLHttpRequest();
		const url = "../board/comment";
		
		const params = `mode=edit&commentNm=${commentNm}&content=${encodeURIComponent(content)}`;
		xhr.open("POST", url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send(params);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				const result = JSON.parse(xhr.responseText);
				if (result.message) {
					alert(result.message);
				}
				
				if (result.success) { // 댓글 수정 완료 -> 새로 고침 
					location.reload();
				}
			}
		};
	}
};

window.addEventListener("load", function() {
	comment.moveToComment();
	
	/** 회원 전용 버튼 클릭시 */
	const list = document.querySelectorAll(".login_only");
	list.forEach(function(el) {
		el.addEventListener("click", function(e) {
			e.preventDefault();
			alert("로그인이 필요한 서비스입니다.");
			location.href='../member/login';
		});
	});
	
	/** 댓글 수정 클릭 처리  */
	const editList = document.querySelectorAll(".edit_comment");
	editList.forEach(function(el) {
		el.addEventListener("click", comment.loadEditForm);
	});
	
	/** 댓글 입력 글자수 제한 */
	const commentEl = document.querySelector(".comment #content");
	if (commentEl) {
		commentEl.addEventListener("keyup", function(e) {
			const target = e.target;
			const content = target.value;
			const charCnt = content.length;
			if (charCnt > 200) {
				target.value = content.substring(0, 200);
			}
			const caret = document.querySelector(".comment .caret");
			if (caret) {
				const bytes = target.value.length * 3;
				caret.innerText = bytes.toLocaleString(); 
			}
		});
	}
});



