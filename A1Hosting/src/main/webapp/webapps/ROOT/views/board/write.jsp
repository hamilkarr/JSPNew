<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.models.Dto" %>

<script src="${rootURL}/resources/js/form.js"></script>
<link href='${rootURL}/resources/css/write.css' rel='stylesheet' type='text/css' />

<div class="writebox">
<form class="write-form" name='writeFrm' method="post" action="../board/${mode}" target='ifrmHidden' autocomplete='off'>
	<c:choose>
		<c:when test="${board == null}">
			<input type="hidden" name="gid" value="${gid}">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="gid" value="${board.gid}">
		</c:otherwise>
	</c:choose>
	<c:if test="${board != null }">
		<input type="hidden" name="postNm" value="${board.postNm}">
	</c:if>	
		<div class="category_box">
			<select name="status" class="select_btn">
				<option value="">전체</option>
				<option value="normal" <c:if test="${board.status == 'normal'}"> selected</c:if>>일반</option>
				<option value="tip" <c:if test="${board.status == 'tip'}"> selected</c:if>>팁과 정보</option>
				<c:if test="${member.memLv == 'admin'}">
					<option value="notice" <c:if test ="${board.status == 'notice'}">selected</c:if>>공지사항</option>
					<input type="hidden" name="isNotice" value=1 />
				</c:if>
			</select>
		</div>
		<div class="text_box">
			<input type="text" name="postTitle" placeholder="제목을 입력해주세요." value="<c:out value='${board.postTitle}' />">
		</div>
		<div>
			<textarea id='content' name="content"><c:out value="${board.content}" /></textarea>
			<span class='addImage'>이미지 추가</span>
			<ul id="attached_files">
			<c:if test="${files != null}">
				<c:forEach var="file" items="${files}" varStatus="status">
					<li class='file_box' data-idx='${file.idx}' data-url='${file.uploadURL}'>
						<a href='#'>${status.count}. ${file.fileName}</a>
						<i class='xi-close remove'></i>
						<i class='xi-file-upload-o insert_editor'></i>
					</li>
				</c:forEach>
			</c:if>
			</ul>
		</div>
			<br>
			<br>
		<a href="../board/list"  class = "btn_list">취소하기</a>
	<c:choose>
		<c:when test='${board == null}'>
		<input type="submit" value="작성하기">
		</c:when>
		<c:otherwise>
		<input type="submit" value="수정하기">
		</c:otherwise>
	</c:choose>
</form>
</div>
<script type="text/html" id="template_file_box">
	<li class='file_box' data-idx='#idx' data-url='#url'>
		<a href='#downloadURL'>#count. #fileName</a>
		<i class='xi-close remove'></i>
		<i class='xi-file-upload-o insert_editor'></i>
	</li>
</script>
