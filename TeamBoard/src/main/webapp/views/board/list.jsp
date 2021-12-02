<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String pagingHtml = (String)request.getAttribute("pagingHtml");
String skey= request.getParameter("skey");
String sopt = request.getParameter("sopt");
String total = (String)request.getAttribute("total");
%>
<c:set var="skey" value="<%=skey%>" />
 
<!-- 게시판 목록S -->
<div class="inner_box">
  <h3>플레이어 게시판</h3>
  <div class="bord_tit">
    <ul class="tit_list">
      <li><a href="${rootURL}/board/list">전체</a></li>
      <li class="tit_link">
      	<a href="${rootURL}/board/list?status=normal">일반</a>
      </li>
      <li class="tit_link">
      	<a href="${rootURL}/board/list?status=tip">팁과 정보</a>
      </li>
    </ul>
  </div>
<!-- 검색 결과 박스. 평소에는 숨김 -->	
  <c:if test="${skey != null}" > <!-- c:if 조건식에 문제가 있는듯... -->
	  <div class="search_resultBox">
	  	<em><%=skey%></em>" 에 대한 " <em>${total} <%-- "${list.size();}" --%> </em> "개의 게시글이 검색되었습니다. "
	  	<a href="../board/list" class="link_back">돌아가기</a>
	  </div>
  </c:if>
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
      <c:forEach var="item" items="${list}"> <!-- items 에 searchList || list 조건문 설정 -->
      	<c:choose>
      		<c:when test="${item.isNotice == 1}">
      			<tr class="tr_list notice">
      		</c:when>
      		<c:otherwise>
        		<tr class="tr_list">
        	</c:otherwise>
        </c:choose>
          <td>
          	<c:choose>
	      		<c:when test="${item.status == 'normal'}">
	      			<c:out value="일반"/>
	      		</c:when>
	      		<c:when test="${item.status == 'tip'}">
	      			<c:out value="팁과 정보"/>
	      		</c:when>
	      		<c:otherwise>
	        		<c:out value="공지사항"/>
	        	</c:otherwise>
        	</c:choose>
          </td>
          <td>
            <a href="view?postNm=${item.postNm}">
            	<c:out value="${item.postTitle}" />
            	<c:if test="${item.commentCnt > 0}">
            	<span class='comment_cnt'>
            		[<fmt:formatNumber value="${item.commentCnt}" />]
            	</span>
            	</c:if>
            	<c:if test="${item.isNew}">
            		N
            	</c:if>
            </a>
          </td>
          <td>
          	<c:choose>
	      		<c:when test="${item.memId == 'administartor'}">
	      			<c:out value="관리자"/>
	      		</c:when>
	      		<c:otherwise>
	        		<c:out value="${item.memId}"/>
	        	</c:otherwise>
        	</c:choose>
          </td>
          <td><c:out value="${item.regDtSt}" /></td>
          <td><fmt:formatNumber value="${item.viewCnt}" /></td>
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
    <form name="searchForm" method="get" action="?" >
    	<select name="sopt">    	
    		<option value="postTitle"${sopt.equals("postTitle")?" selected":""}>제목 </option>
    		<option value="postTitle_content"${sopt.equals("postTitle_content")?" selected":""}>제목 + 본문</option>
    		<option value="memId"${sopt.equals("memId")?" selected":""}>작성자</option>
    	</select>
    	<input type="text" name="skey" value='${skey}' placeholder="검색어를 입력하시죠..." />
    	<input type="submit" value="검색" />
    </form>
  </div>  
</div>
<!-- 게시판 목록E -->
