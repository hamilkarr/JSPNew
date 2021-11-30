<%@ page contentType="text/html; charset=utf-8" %>
<%@ page  import="com.models.member.*" %>
<%@ page import="com.models.board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
	boolean isLogin = (Boolean)request.getAttribute("isLogin");
	Member member = (Member)request.getAttribute("member");
	Board view = (Board)request.getAttribute("view");
%>
<c:set var="rootURL" value="<%=rootURL%>" />
<c:set var="isLogin" value="<%=isLogin%>" />
<c:set var="member" value="<%=member%>" />
<c:set var="view" value="<%=view%>" />

<div class="layout_width">
    <h3>플레이어 게시판</h3>
    <div class="view_head">
        <div class="head_tit"><c:out value="${view.postTitle}"/></div>
            <div class="info_head">
                <dl class="left">
                    <dt>작성자</dt>
                    <span class="ico_board_tier gold"></span>
                    <dd><a href="">
                    <c:choose>
			      		<c:when test="${view.memId == 'administartor'}">
			      			<c:out value="관리자"/>
			      		</c:when>
			      		<c:otherwise>
			        		<c:out value="${view.memId}"/>
			        	</c:otherwise>
        			</c:choose>
        		</a></dd> <!-- a 태그 아이디 정보 팝업?(list처럼)  -->
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
        <div class="view_content">${view.content}</div>
        <div class="view_btns">
            <a href="list" class="btn_list"><i class="xi-bars xi-x"></i></a>
            <c:choose>
                <c:when test="${view.memId == member.memId}"> <!-- 본인 글일때 -->
                    <a class="btn_write" href="edit?postNm=${view.postNm}">수정하기</a>
                    <a href='delete?postNm=${view.postNm}' class="btn_write" onclick="return confirm('정말 삭제하시겠습니까?');" target='ifrmHidden'>삭제하기</a>
                </c:when>
                <c:otherwise>
                    <a class="btn_write" href='write'">글쓰기</a>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- 댓글 -->
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
        <form class="form_cmt" action="">
                <textarea  class="cmt_box" name="" id="" cols="30" rows="10"></textarea>
                <c:choose>
                    <c:when test="${isLogin}"> <!-- 로그인 O -->
                        <div class="refer_cmt">댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.</div> <!-- div는 focus 안됨.. 추후 수정 -->
                    </c:when>
                    <c:otherwise> <!-- 로그인 X -->
                        <div class="refer_cmt">로그인후 이용하실 수 있습니다.</div>
                    </c:otherwise>
                </c:choose>
                <button type="submit" class="cmt_submit">등록</button>
            </form>
            <!-- 댓글 list -->
            <div class="list_cmt">
                <div class="left_cmt">
                    <div class="user_cmt">
                        <span class="ico_board_tier silver"></span>
                        <span><c:out value="${member.memId}" />아이디</span> <!-- a 태그 아이디 정보 팝업?(list처럼)  -->
                    </div>
                    <div class="date_cmt">21.11.25</div> <!-- dto // 최근 24시간 내 댓글은 시간으로 나오는듯. ex) 10:44 -->
                </div>
                <span class="border_cmt"></span>
                <div class="right_cmt">
                     into electronicinto electronic
                </div>
            </div>
            <!-- 댓글 페이징 들어갈 자리-->
        </div>
    </div>