<%@ page contentType="text/html; charset=UTF-8"%>
<%
	try {
		int num1 = 10;
		int num2 = 0;
		int result = num1 / num2;
		
	} catch (ArithmeticException e) {
		RequestDispatcher rd = request.getRequestDispatcher("/ch11/ex01_error2.jsp");
		rd.forward(request, response);
	}
%>