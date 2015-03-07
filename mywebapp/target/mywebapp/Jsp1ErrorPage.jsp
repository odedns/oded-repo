<%@ page contentType="text/html; charset=windows-1255" %>
<%@ page isErrorPage="true" %>
<HTML>
<BODY>

<H1>Error page Jsp1</H1>

<BR>An error occured in the bean. Error Message is: <%= exception.getMessage() %><BR>
Stack Trace is : <PRE><FONT COLOR="RED"><% 
 java.io.CharArrayWriter cw = new java.io.CharArrayWriter(); 
 java.io.PrintWriter pw = new java.io.PrintWriter(cw,true); 
 exception.printStackTrace(pw); 
 out.println(cw.toString()); 
 %></FONT></PRE>
<BR></BODY>
</HTML>
