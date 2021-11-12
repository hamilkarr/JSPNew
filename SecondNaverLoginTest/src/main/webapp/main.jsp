<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.models.dto.Member" %>
<%
	Member member = (Member)request.getAttribute("member");
	String naverCodeURL = (String)request.getAttribute("naverCodeURL");
%>
<c:set var="member" value="<%=member%>" />
<c:choose>
	<c:when test="${member == null}">
		<h2>네이버 아이디로 로그인 테스트 페이지 입니다.<h5>(이걸 또 다시 할 줄이야... 신난다!!!.....)</h5></h2>
		
		<a href='member/join'>회원가입</a>
		<a href='member/login'>로그인</a>
		<a href='member/login'><img src='./public/img/naverlogin_btn.png' height='45' ></a>
	</c:when>
	<c:otherwise>
	  <c:out value="${member.memNm}" />
	  (<c:out value="${member.memId}" />)님 로그인....
	  <a href='member/logout'>로그아웃</a>
	</c:otherwise>
</c:choose>