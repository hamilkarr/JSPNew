<%@ page contentType="text/html; charset=utf-8" %>
<%@ page  import="com.models.member.*" %>
<%@ page import="com.models.board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="layout_width">
    <h3>플레이어 게시판</h3>
    <div class="view_head">
        <div class="head_tit"><c:out value="${view.postTitle}"/></div>
            <div class="info_head">
                <dl class="left">
                    <dt>작성자</dt>
                    <dd><a href=""><c:out value="${view.memId}" /></a></dd> <!-- a 태그 아이디 정보 팝업?(list처럼)  -->
                </dl>
                <dl class="right_f">
                    <dt>작성일</dt>
                    <dd><c:out value="${view.regDt}" /></dd> <!-- dto -->
                </dl>     
                <dl class="right">
                    <dt>조회수</dt>
                    <dd>6</dd> 
                </dl>
            </div>
        </div>
        <div class="view_content">        
        <c:out value="${view.content }"/> <!-- 작성 글 내용 연동 -->
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
        </div>
        <div class="view_btns">
            <a href="" class="btn_list"><i class="xi-bars xi-x"></i></a>
            <c:choose>
                <c:when test="${isLogin && member.memId == view.memId}"> <!-- 본인 글일때 -->
                    <a class="btn_write" href="edit?postNm=${view.postNm}">수정하기</a>
                </c:when>
                <c:otherwise>
                    <a class="btn_write" onclick="location.href='write'">글쓰기</a>
                </c:otherwise>
            </c:choose>
       <%-- <a href="edit?postNm=${view.postNm}"><input type="button" value="글 수정"></a>

			<a href="delete?postNm=${view.postNm}" onclick="return confirm('정말 삭제하시겠습니까?');" target='ifrmHidden'>
			<input type="button" value="삭제하기"></a> --%>
        </div>
        
        <!-- 댓글 -->
        <jsp:include page="_comment.jsp" />

    </div>
