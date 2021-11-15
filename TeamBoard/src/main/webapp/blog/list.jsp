<%@ page contentType="text/html; charset=utf-8" %>
<script src="../resources/js/paging.js"></script>

<!--Kanban/views/outline/header/inc/common.jsp-->
<!--메인페이지 상단 S -->
<header>
	<div class="main_top layout_width">
			<a href="#">로그인</a>
			<a href="#">회원가입</a>
	</div>
</header>
<nav>
	<ul class='nav_list layout_width'>
		<li class="box">
			<a class="nav_over" href="#">새소식</a>
			<ul class="show-bar">
				<li><a href="#">공지사항</a></li>
				<li><a href="#">GM소식</a></li>
				<li><a href="#">이벤트</a></li>
				<li><a href="#">업데이트</a></li>
				<li><a href="#">대회소식</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">가이드</a>
			<ul class="show-bar">
				<li><a href="#">게임가이드</a></li>
			</ul>
		</li> 
		<li class="box">
			<a class="nav_over" href="#">커뮤니티</a>
			<ul class="show-bar">
				<li><a href="#">플레이어 게시판</a></li>
				<li><a href="#">미디어 게시판</a></li>
				<li><a href="#">DUO/SQUAD 모집</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">SNS</a>
			<ul class="show-bar">
				<li><a href="#">디스코드</a></li>
				<li><a href="#">페이스북</a></li>
				<li><a href="#">유튜브</a></li>
				<li><a href="#">톡채널</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">랭킹</a>
			<ul class="show-bar">
				<li><a href="#">랭킹</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">상점</a>
			<ul class="show-bar">
				<li><a href="#">게임구매</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">자료실</a>
			<ul class="show-bar">
				<li><a href="#">다운로드</a></li>
			</ul>
		</li>
		<li class="box">
			<a class="nav_over" href="#">고객센터</a>
			<ul class="show-bar">
				<li><a href="#">고객센터</a></li>
				<li><a href="#">이용제한</a></li>
				<li><a href="#">이의신청</a></li>
				<li><a href="#">보안센터</a></li>
			</ul>
		</li>
	</ul>
</nav>
<!--메인페이지 상단 E-->



<div class="post_list">
<h1>게시판 목록</h1>
<table>
	<thead>
		<tr>
			<th>제목</th><th>작성일</th>
		</tr>
	</thead>
	<tbody id='blog_content'>
	<jsp:include page="_list.jsp" />
	</tbody>
</table>
<div id="blog_post">
</div>
</div>
<div class="write-top">
<a href='write'>✏  글쓰기</a>
</div>
<br/>
<button id="button" type="button">다음 게시글</button>