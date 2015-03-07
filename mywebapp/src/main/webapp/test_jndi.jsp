<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="javax.naming.*,javax.jms.*,java.util.*" %>


<META http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="wsdl/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>jndi.jsp</TITLE>
</HEAD>
<BODY>


<% String msg = ""; 
	String msg2 = "";
	String err = "";
	String url = request.getParameter("url");
	System.out.println("url=" +url);
	String lookup = request.getParameter("lookup");
	System.out.println("lookup =" + lookup);
	if(url != null) {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
			"com.ibm.websphere.naming.WsnInitialContextFactory");
		prop.put(Context.PROVIDER_URL, url);
		try {
			InitialContext ctx = new InitialContext(prop);
			msg = "successfuly connected to " + url;
			if(lookup != null && lookup.length() > 0) {
				Object o = ctx.lookup(lookup);
				if(o == null) {
					msg2 = "Cannot find: " + lookup;
				} else {
					msg2 =  "Found : " + o.getClass().getName();
				}
			}
			ctx.close();
		} catch(Exception e) {
			System.out.println(e);
			err = e.getMessage();
		}
	} // if
	
%>

<P>JNDI Test.</P>

<FORM action="/myweb/test_jndi.jsp">
<TABLE>
<tr>
	<td>Enter JNDI URL:</td><td><INPUT type="text" name="url"  size="50"/></td>
</tr>
<tr>
	<td>lookup key:</td> <td><INPUT type="text" name="lookup" size="50"/></td>
</tr>
<tr>
<td>&nbsp</td>
<td><INPUT TYPE="submit" NAME="submit" VALUE="Submit Form">
<INPUT TYPE="reset" NAME="reset" VALUE="Clear Form">
</td>
</tr>
</FORM>

<tr>
	<td>
	Message :</td><td> <%=msg %>
	</td>
</tr>
<tr>
<td>
Message :</td><td> <%=msg2 %></td>
</tr>
<tr>
<td>
Error:</td><td>  <%=err %></td>
</td>
</tr>
</table>
</BODY>

</HTML>