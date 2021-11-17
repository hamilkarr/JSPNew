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

<!-- 배너S -->
<div class="swiper mySwiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
        	<img src='../resources/css/banner/kakao.jpeg'>
        </div>
        <div class="swiper-slide">Slide 2</div>
        <div class="swiper-slide">Slide 3</div>
        <div class="swiper-slide">Slide 4</div>
      </div>
      <div class="swiper-pagination"></div>
</div>
<!-- 배너E -->
<!-- 게시판 목록S -->
<div class="inner_box">
	<h3>게시판 목록</h3>
	<div class="bord_tit">
		<ul class="tit_list">
			<li><a href="#">전체</a></li>
			<li class="tit_link"><a href="#">일반</a></li>
			<li class="tit_link"><a href="#">팁과 정보</a></li>
		</ul>
	</div>
	<table class="board_table">
		<thead>
			<tr class="table_tit">
				<th>구분</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody class='board_content'>
		<jsp:include page="_list.jsp" />
		</tbody>
	</table>
	<div id="board_bttom">
		<a class="page" href="#">다음 페이지</a>
		<button class="write_btn">
			<a class="write" href="write">글쓰기</a>
		</button>
	</div>
</div>
<!-- 게시판 목록E -->
      