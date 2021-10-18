<%@ page contentType="text/html; charset=utf-8" %>
<h1>회원 추가</h1>
<form method="post" action="ex09_process.jsp">
	<dl>
		<dt>아이디</dt>
		<dd><input type="text" name="memId"></dd>
	</dl>
	<dl>
		<dt>회원명</dt>
		<dd><input type="text" name="memNm"></dd>
	</dl>
	<input type="submit" value="회원 추가">
</form>