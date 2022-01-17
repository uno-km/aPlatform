<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/includes/jstl.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		<c:out value="${error}" />
	</h2>
	<h2>
		<c:out value="${logout}" />
	</h2>
	<form method='post' action="/login">
		<input type='text' name='username' value='qwe'>
		<input type='password' name='password' value='qwe'>
		<input type='submit'>
		<input type='hidden' name='${_csrf.parameterName}'
			value='${_csrf.token}'>
	</form>
</body>
</html>