<%@page import="java.util.*" %>
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>New Page 1</title>
</head>

<body>

<% 
	out.println("test jsp page");
	if(session.isNew() ) {
		out.println("session is new ..");
	}else {
		out.println("existing session id = " + session.getId());
	}
	out.println(request.toString());
	Enumeration e = request.getHeaderNames();
	while(e.hasMoreElements()) {
%>
<p><% 
	 String s = (String)e.nextElement();	
	 out.println(s + "\t" + request.getHeader(s));
	}
%>
<form method="POST"
	action=<%= response.encodeRedirectUrl("/servlet/genhttpservlet?next_page=/mysession.jsp") %>>
<p><input type="submit" value="Reload Page" name="B1"></p>
</form>

</body>

</html>
