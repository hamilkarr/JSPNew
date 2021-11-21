<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="java.util.ArrayList" %>
<%@ page import="com.models.Dto" %> --%>
<%
	String rootURL = (String)request.getAttribute("rootURL");
%>
<c:set var="rootURL" value="<%=rootURL%>" />

<script src="../resources/js/paging.js"></script>

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
		<tbody class="board_content">
			<tr class="tr_list">
				<td>구분</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
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