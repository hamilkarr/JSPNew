<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
%>
<h1>${view.postTitle}</h1>
<h1>${view.memId }</h1>
<h1>${view.regDt}</h1>
<h3>${view.content }</h3>
<a href="edit?postNm=${view.postNm}">
<input type="button" value="글 수정"></a>

<a href="delete?postNm=${view.postNm}" onclick="return confirm('정말 삭제하시겠습니까?');" target='ifrmHidden'>
<input type="button" value="삭제하기"></a>


