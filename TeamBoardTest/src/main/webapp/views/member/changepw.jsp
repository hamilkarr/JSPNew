<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 임시로 css 루트 적용 S -->
<%
	String rootURL = (String)request.getAttribute("rootURL");
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<link href='<%=rootURL%>/resources/css/member.css' rel='stylesheet' style= 'text/css' />
<!-- 임시로 css 루트 적용 E -->

<main>
	<div class="changepw_box loginbox">
		<div class="member_tit">비밀번호 변경</div>
		<form method="post" action="../member/change_pw" target="ifrmHidden" autocomplete="off">
			<input type="password" name="memPw" placeholder="변경할 비밀번호">
			<input type="password" name="memPwRe" placeholder="비밀번호 확인">
			<input type="submit" value="변경하기" onclick="return confirm('정말 변경하시겠습니까?');">
		</form>
	</div>	
</main>