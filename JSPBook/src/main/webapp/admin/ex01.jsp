<%@ page contentType="text/html; charset=UTF-8"%>
사용자명: <%=request.getRemoteUser()%><br>
인증방법: <%=request.getAuthType()%><br>
Manager에 속하는 role 사용자인가?
<%=request.isUserInRole("manager")%>