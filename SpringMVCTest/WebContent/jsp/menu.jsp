<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>

<h2>Menu</h2>

<div id="menu">

<ul>

<s:url var = "editEmp" value="/editEmp"/>
<s:url var = "listEmp" value="/listEmp"/>
<li><a href="${editEmp}" >Add Emp</a></li> 
<li><a href="${listEmp}" >List Emp</a></li>

</ul>
</div>

</body>
</html>