<%@ page contentType="text/html; charset=utf-8" %>
<%@ page  import="com.models.member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
	boolean isLogin = (Boolean)request.getAttribute("isLogin");
	Member member = (Member)request.getAttribute("member");
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<c:set var="isLogin" value="<%=isLogin%>" />
<c:set var="member" value="<%=member%>" />
<header>
	<div class="main_top">
		<div class='inner layout_width'>
		<c:choose>
			<c:when test="${isLogin}">
				<div class="profile">안녕하세요! <a href="${rootURL}/member/info"> <c:out value="${member.memId}" /></a>님</div>
				<a class="islogin logout" href="${rootURL}/member/logout">
					<span>로그아웃</span>
				</a>
			</c:when>
			<c:otherwise>
				<a class="islogin" href="${rootURL}/index.jsp">
					<i class="xi-user-o"></i>
					<span>로그인</span>
				</a>
			</c:otherwise>
		</c:choose>
		</div>
	</div>
	<nav>
		<ul class='nav_list'>
				<div class="inner-bar"></div>
				<a class="main_logo" href="${rootURL}/board/list"><img src="https://t1.kakaocdn.net/gamepub/pub/battlegrounds/images/logo_pubg2-1.png" alt="logo"></a>
			<li class='box'>
				<a class="nav_over" href="${rootURL}/kanban/work">새소식</a>
				<ul class="show-bar">
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
				</ul>
			</li>
			<li class='box'>
				<a class="nav_over" href="${rootURL}/kanban/list?status=ready">가이드</a>
				<ul class="show-bar">
					<li><a href="#">준비중</a></li>
				</ul>
			</li>
			<li class='box'>
				<a class="nav_over" href="${rootURL}/kanban/list?status=progress">커뮤니티</a>
				<ul class="show-bar">
					<li><a href="${rootURL}/board/list">플레이어 게시판</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#"></a></li>
				</ul>
			</li>
			<li class='box'>
				<a class="nav_over" href="${rootURL}/kanban/list?status=done">SNS</a>
				<ul class="show-bar">
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
					<li><a href="#">준비중</a></li>
				</ul>
			</li>
			<li class='box'>
			<a class="nav_over" href="${rootURL}/kanban/work">랭킹</a>
			<ul class="show-bar">
				<li><a href="#">준비중</a></li>
			</ul>
		</li>
		<li class='box'>
			<a class="nav_over" href="${rootURL}/kanban/list?status=ready">상점</a>
			<ul class="show-bar">
				<li><a href="#">준비중</a></li>
			</ul>
		</li>
		<li class='box'>
			<a class="nav_over" href="${rootURL}/kanban/list?status=progress">자료실</a>
			<ul class="show-bar">
				<li><a href="#">준비중</a></li>
			</ul>
		</li>
		<li class='box'>
			<a class="nav_over" href="${rootURL}/kanban/list?status=done">고객센터</a>
			<ul class="show-bar">
				<li><a href="#">준비중</a></li>
				<li><a href="#">준비중</a></li>
				<li><a href="#">준비중</a></li>
				<li><a href="#">준비중</a></li>
			</ul>
		</li>
	</ul>
	</nav>
</header>
<!-- 배너S -->
<div class="swiper mySwiper">
	<div class="swiper-wrapper">
	  <div class="swiper-slide">
		  <img src='${rootURL}/resources/css/banner/banner0.jpeg'>
	  </div>
	  <div class="swiper-slide">
		  <img src='${rootURL}/resources/css/banner/banner1.jpeg'>
	  </div>
	  <div class="swiper-slide">
		  <img src='${rootURL}/resources/css/banner/banner2.jpeg'>
	  </div>
	  <div class="swiper-slide">
		  <img src='${rootURL}/resources/css/banner/banner3.jpeg'>
	  </div>
	</div>
	<div class="swiper-pagination"></div>
</div>
<!-- 배너E -->


