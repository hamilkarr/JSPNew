<%@ page contentType="text/html; charset=utf-8" %>
<form name="frmAdd" id="frmAdd" method="post" action="../kanban/add" target="ifrmHidden" autocomplete="off">
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
</form>