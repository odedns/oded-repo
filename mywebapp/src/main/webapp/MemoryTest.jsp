<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<META http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>MemoryTest</TITLE>
</HEAD>


<%!
	float formatMem(long mem) 
	{
		mem/= 1000;
		float f = (float) mem /1000;
		return(f);
	}
%> 


<BODY>
<h3> IpAddress: <%=request.getLocalName() %>
<H2>Memory  Test Page.</H2>
<TABLE border="3">
<tr>
	<td> Max Memory:</td> 
	 <td><%= formatMem(Runtime.getRuntime().maxMemory()) %> MB</td>
	 <td>Maximum memory available to JVM </td>
</tr>
<tr>
	<td> Total Memory:</td> 
	 <td><%= formatMem(Runtime.getRuntime().totalMemory()) %> MB</td>
	 <td>Memory used by JVM </td>
</tr>
<tr>
	<td> Free Memory:</td> 
	 <td><%= formatMem(Runtime.getRuntime().freeMemory()) %> MB</td>
	 <td>Free memory in JVM </td>
</tr>

</TABLE>
</BODY>
</HTML>
