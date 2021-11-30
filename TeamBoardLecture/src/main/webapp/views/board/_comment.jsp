<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="newline" value="\n"></c:set>

<div class="comment">
   <div class="tit_cmt">
       <div class="num_cmt">
           <span>댓글</span>
            <span>2</span> <!-- 댓글 갯수 연동 -->
        </div>
        <div class="byte_info"> <!-- form 태그 안에 넣어야하나? -->
            <span>0</span> <!-- 글자수 연동 -->
             <span>/ 600 bytes (한글 300자) | </span>
              <a href="">댓글 운영정책</a>
         </div>
     </div>
     <!-- 로그인 후 댓글 작성 가능 -> 알림창(로그인 필요, 로그인 go) 뜨고 로그인 페이지로 이동 -->
     <form class="form_cmt" action="../board/comment" target="ifrmHidden" autocomplete="off">
     	 <input type="hidden" name="postNm" value="${view.postNm}" method="post">
          <textarea class="cmt_box" name="content" id="" cols="30" rows="10"></textarea>
            <c:choose>
               	<c:when test="${isLogin}"> <!-- 로그인 O -->
                 <div class="refer_cmt">댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.</div> <!-- div는 focus 안됨.. 추후 수정 -->
                  <button type="submit" class="cmt_submit">등록</button>
                </c:when>
                <c:otherwise> <!-- 로그인 X -->
                    <div class="refer_cmt">로그인후 이용하실 수 있습니다.</div>
                     <button type="submit" class="cmt_submit" disabled>등록</button>
                 </c:otherwise>
              </c:choose>
            </form>
            <!-- 댓글 list -->
            <c:forEach var="item" items="${comments}">
            <div class="list_cmt">
                <div class="left_cmt">
                    <div class="user_cmt">
                        <span class="ico_board_tier silver"></span>
                        <div><c:out value="${item.memId}(아이디)" /></div> <!-- a 태그 아이디 정보 팝업?(list처럼)  -->
                    </div>
                    <div class="date_cmt">${item.regDt }</div> <!-- dto -->
                </div>
                <span class="border_cmt"></span>
                <div class="right_cmt">
                     ${item.content }
                </div>
            </div>
            </c:forEach>
            <!-- 댓글 페이징 들어갈 자리-->
        </div>