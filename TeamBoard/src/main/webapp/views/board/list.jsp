<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String rootURL = (String) request.getAttribute("rootURL");
String pagingHtml = (String) request.getAttribute("pagingHtml");
%>
<c:set var="rootURL" value="<%=rootURL%>" />

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
        <th class="th_tit">제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody class="board_content">
      <c:forEach var="item" items="${list}">
      	<c:choose>
      		<c:when test="${item.isNotice == 1}">
      			<tr class="tr_list notice">
      		</c:when>
      		<c:otherwise>
        		<tr class="tr_list">
        	</c:otherwise>
        </c:choose>
          <td><c:out value="${item.status}"/></td>
          <td>
            <a href="view?postNm=${item.postNm}"><c:out value="${item.postTitle}" /></a>
          </td>
          <td><c:out value="${item.memId}" /></td>
          <td><c:out value="${item.regDt}" /></td>
          <td>조회수</td>
      </c:forEach>
    </tbody>
  </table>
	<div>
		아이콘 모음<i class='xi-naver-square'></i>
		<span class="ico_board_tier silver"></span>
		<span class="ico_board_tier gold"></span>
		<span class="ico_board_tier platinum"></span>
		<span class="ico_board_tier bronze"></span>
		</div>
  <div id="board_bttom">
    <%=pagingHtml%>
    <button class="write_btn" onclick="location.href='write'">글쓰기</button>
  </div>
</div>
<!-- 게시판 목록E -->
