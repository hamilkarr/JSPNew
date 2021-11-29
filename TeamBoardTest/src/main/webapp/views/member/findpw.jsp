<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 임시로 css 루트 적용 S -->
<%
	String rootURL = (String)request.getAttribute("rootURL");
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<link href='<%=rootURL%>/resources/css/member.css' rel='stylesheet' style='text/css' />
<!-- 임시로 css 루트 적용 E -->

<main>
	<div class="findpw_box loginbox">
		<div class='member_tit'>비밀번호 찾기</div>
		<form method="post" action="../member/findpw" autocomplete="off" target="ifrmHidden">
			<input type="text" name="memId" placeholder="아이디">
			<input type="text" name="memNm" placeholder="회원명">
			<input type="text" name="memPwHint" placeholder="비밀번호 찾기 힌트">
			
			<input type="submit" value="비밀번호 찾기">
		</form>
	</div>
</main>