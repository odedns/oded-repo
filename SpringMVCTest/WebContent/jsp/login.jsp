<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<br>
<br>
<form action="/SpringMVCTest/dologin" method="post">

<table align="center">
<tr>
<td>
Login: 
</td>
<td>
<input type="text" name="userid"/>
</td>
</tr>
<tr>
<td>
Password: 
</td>

<td><input type="password" name="password" /></td>
</tr>
<tr>
<td>
<input type="submit" id="submitButton" />
</td>
<td>
<input type="reset" id="clearButton" />
</td>
</tr>


</table>
</form>
</body>
</html>