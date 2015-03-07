<% 
	response.setContentType("text/vnd.wap.wml");

	out.println(" <?xml version=\"1.0\"?> ");
	out.println(" <!DOCTYPE wml PUBLIC \"-//WAPFORUM//DTD WML 1.1//EN\" ");
	out.println(" \"http://www.wapforum.org/DTD/wml_1.1.xml\"> "); 
 
	out.println("<wml>");
	out.println("<card id=\"mobiledate\" >");
	
	out.println("<p> Date is: " + new java.util.Date() + "</p>");
	out.println("</card>");
	out.println("</wml>");
%>
