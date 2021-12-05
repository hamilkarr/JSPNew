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
	<!--로그인 전 로그인 버튼만 // 로그인 후, 안녕하세요! ooo님 
		로그아웃// OOO님(hover 밑줄) 클릭하면 회원정보 수정페이지로 넘어감.-->
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
				<a class="islogin" href="${rootURL}">
					<i class="xi-user-o"></i>
					<span>로그인</span>
				</a>
				<!-- 로그인창에서 회원가입 -->
				<!-- <a href="${rootURL}/member/join">회원가입</a> -->
			</c:otherwise>
		</c:choose>
		</div>
	</div>
<!--com/models/kanban/kanban.java-->
<!--show-bar htrml 구조 변경? 드롭다운 문제, 해결해야함.-->
<!-- class='on' 클릭했을때, 링크이동할때 색변함 ??
	-> 게시판 목록 전체 | 일반 | 팁과 정보 에 적용 하면 될듯-->
	<nav>
		<ul class='nav_list layout_width'>
			<div class="inner-bar inner-bar-move"></div>
 
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


