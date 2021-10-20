<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.core.Logger" %>
<%
	//Logger.init("dev", "D:\\logs");
	//Logger.log("테스트 로그1");
	//Logger.log("테스트 로그2");
	//Logger.log("테스트 로그3");
	Logger.setWriter(out);
	Logger.setPreventClosed(true);
	
	try {
		throw new Exception("에러 발생!!!");
	} catch (Exception e) {
		Logger.log(e);
	}
%>