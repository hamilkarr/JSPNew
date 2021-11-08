<%@ page contentType="text/html; charset=utf-8" %>
<form name="frmAdd" id="frmAdd" method="post" action="../kanban/add" target="ifrmHidden" autocomplete="off" enctype="multipart/form-data">
	<input type="hidden" name="gid" value="${gid}" />
	<dl>
		<dt>작업구분</dt>
		<dd>
			<input type="radio" name="status" value="ready" id="status_ready" checked>
			<label for="status_ready">준비중</label>
			<input type="radio" name="status" value="ready" id="progress">
			<label for="progress">진행중</label>
			<input type="radio" name="status" value="ready" id="done">
			<label for="done">완료</label>
		</dd>
	</dl>
	<dl>
		<dt>제목</dt>
		<dd>
			<input type="text" name="subject">
		</dd>			
	</dl>
	<dl>
		<dt>작업내용</dt>
		<dd>
			<textarea name="content" rows="" cols=""></textarea>
		</dd>
	</dl>
	<dl>
		<dt>파일첨부
			<span class="del_file"><i class='xi-file-remove-o'></i>삭제</span>
			<span class="add_file"><i class="xi-file-add-o"></i>추가</span>
		</dt>
		<dd id="file_upload">
			<div class='rows'><input type="file" name="file1" ></div>			
		</dd>
	</dl>
	<input type="submit" value="작업등록">
</form>

