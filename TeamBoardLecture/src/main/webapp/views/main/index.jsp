<%@ page contentType="text/html; charset=utf-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <% String rootURL =
(String)request.getAttribute("rootURL"); String naverCodeURL =
(String)request.getAttribute("naverCodeURL"); String kakaoCodeURL =
(String)request.getAttribute("kakaoCodeURL"); %>
<c:set var="rootURL" value="<%=rootURL%>" />
<c:set var="naverCodeURL" value="<%=naverCodeURL%>" />
<c:set var="kakaoCodeURL" value="<%=kakaoCodeURL %>" />
<!--임시로 css경로 적어둠  -->
<link href="<%=rootURL%>/resources/css/member.css" rel="stylesheet" type="text/css" />
<main>
  <div class="loginbox">
    <div class="member_tit">로그인</div>
    <form
      name="frmLogin"
      id="frmLogin"
      method="post"
      action="${rootURL}/member/login"
      target="ifrmHidden"
      autocomplete="off"
    >
      <input type="text" name="memId" placeholder="아이디" />
      <input type="password" name="memPw" placeholder="비밀번호" />

      <input type="submit" value="로그인" />
      <div class="findjoin_link">
        <div class="left">
          <a href="${rootURL}/member/findid">아이디 찾기</a>
          <a href="${rootURL}/member/findpw">비밀번호 찾기</a>
        </div>
        <div class="right">
          <a href="${rootURL}/member/join">회원 가입</a>
        </div>
      </div>
      <div class="snsline">SNS 로그인</div>
      <br />

      <a href="${naverCodeURL}">
        <img src="${rootURL}/resources/img/naverlogin_btn.png" />
      </a>
      <a href="${kakaoCodeURL}">
        <img src="${rootURL}/resources/img/kakaologin_btn.png" />
      </a>
    </form>
  </div>
</main>
