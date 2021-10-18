<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.io.File" %>

<%
String uploadPath = getServletContext().getRealPath("//upload");
MultipartRequest multi = new MultipartRequest(request, uploadPath, 5 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

Enumeration<String> files = multi.getFileNames();
while(files.hasMoreElements()){
	String name = files.nextElement();
	String filename = multi.getFilesystemName(name);
	String original = multi.getOriginalFileName(name);
	File file = multi.getFile(name);
	out.print("파일 name 속성: " + name + "<br>");
	out.print("업로드된 파일명: " + filename + "<br>");
	out.print("");
}

String subject1 = multi.getParameter("subject1");
String subject2 = multi.getParameter("subject2");
out.print("제목1 :" + subject1 + "<br>");
out.print("제목2 :" + subject2 + "<br>");

Enumeration<String> params = multi.getParameterNames();
while(params.hasMoreElements()) {
	String param = params.nextElement();
	String value = multi.getParameter(param);
	out.print("param= " + param + ", value= " + value + "<br>");
}
%>