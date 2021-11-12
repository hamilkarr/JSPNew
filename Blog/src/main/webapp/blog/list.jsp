<%@ page contentType="text/html; charset=utf-8" %>
<script src="../resources/js/paging.js"></script>
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