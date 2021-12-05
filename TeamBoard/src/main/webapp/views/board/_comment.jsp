<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setAttribute("newline", "\n"); %>
<div class="comment">
   <div class="tit_cmt">
       <div class="num_cmt">
           <span>댓글</span>
            <span>
            	<fmt:formatNumber value="${view.commentCnt}" />
            </span> <!-- 댓글 갯수 연동 -->
        </div>
        <div class="byte_info"> <!-- form 태그 안에 넣어야하나? -->
            <span class="caret">0</span> <!-- 글자수 연동 -->
             <span>/ 600 bytes (한글 200자) | </span>
              <a href="">댓글 운영정책</a>
         </div>
     </div>
     <!-- 로그인 후 댓글 작성 가능 -> 알림창(로그인 필요, 로그인 go) 뜨고 로그인 페이지로 이동 -->
     <form class="form_cmt" action="../board/comment" target="ifrmHidden" autocomplete="off" method="post">
     	 <input type="hidden" name="postNm" value="${view.postNm}">
          <textarea class="cmt_box" name="content" id="content" cols="30" rows="10"></textarea>
            <c:choose>
               	<c:when test="${isLogin}"> <!-- 로그인 O -->
                 <div class="refer_cmt">댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.</div> <!-- div는 focus 안됨.. 추후 수정 -->
                  <button type="submit" class="cmt_submit">등록</button>
                </c:when>
                <c:otherwise> <!-- 로그인 X -->
                    <div class="refer_cmt">로그인후 이용하실 수 있습니다.</div>
                     <button type="submit" class="cmt_submit login_only">등록</button>
                 </c:otherwise>
              </c:choose>
            </form>
            <!-- 댓글 list -->
            <c:forEach var="item" items="${comments}">
            <div class="list_cmt" id="comment_${item.commentNm}">
                <div class="left_cmt">
                    <div class="user_cmt">
                    <c:choose>
	                    <c:when test="${item.memLv == 'admin'}">
	                        <span class="ico_board_tier master"></span>
	                       	<c:out value="관리자"/>
	                     </c:when>
	                     <c:when test="${item.memLv == 'platinum'}">
	                        <span class="ico_board_tier platinum"></span>
	                       	<c:out value="${item.memId}" />
	                     </c:when>
	                     <c:when test="${item.memLv == 'gold'}">
	                        <span class="ico_board_tier gold"></span>
	                       	<c:out value="${item.memId}" />
	                     </c:when>
	                     <c:otherwise>
					      		<span class="ico_board_tier silver"></span>
					      		<c:out value="${item.memId}"/>
					       </c:otherwise>
                    </c:choose>
                    </div>
                    <div class="date_cmt">${item.regDt}</div> <!-- dto -->
                </div>
                <span class="border_cmt"></span>
                <div class="right_cmt">
                    ${fn:replace(item.content, newline, '<br>')}
                </div>
                <c:if test="${item.memId == member.memId}">
                	<a href='../board/delete_comment?commentNm=${item.commentNm}' onclick="return confirm('정말 삭제하시겠습니까?');" target="ifrmHidden">삭제</a>
                	<button type="button" class="edit_comment" data-commentnm='${item.commentNm}'>수정</button>
                	<!-- data-comment-nm 에서 data-commentNm으로 수정 -->
                </c:if>
            </div>
            </c:forEach>
            <!-- 댓글 페이징 들어갈 자리-->
        </div>