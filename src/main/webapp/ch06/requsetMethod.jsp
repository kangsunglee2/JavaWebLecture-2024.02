<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Request Method</title>
	</head>
	<body>
		<h1>HttpServletRequest의 다양한 메소드</h1>
		<hr>
		<ul>
			<li>contextPath=${contextPath}</li>
			<li>method=<%= request.getMethod() %></li>	
			<li>requstUri=${requstUri}</li>
			<li>serverName=${serverName}</li>
			<li>servletPath=${servletPath}</li>
			<li>pathInfo=${pathInfo}</li>
		</ul>
	</body>
</html>