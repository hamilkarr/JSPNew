<%@ page contentType="text/html; charset=utf-8"%>
<h1 class='main_title'>Mini Blog</h1>
<div id="blog_post">
	<jsp:include page="_list.jsp" />
</div>
<button id="button" type="button">더보기</button>
<button id="button2" type="button" onclick="location.href='../blog/post'">글쓰기</button>