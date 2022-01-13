<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type='text' name = 'username' value = 'qwe'>
	<input type='password' name = 'password' value = 'qwe'>
	<input type='submit'>
	<input type='hidden' name = '${_csrf.parameterName}' value = '${_csrf.token}'>
	
</body>
</html>