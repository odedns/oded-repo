<%@page import="java.util.*"  %>

<h2> Property dump:</h2>

<p>

<%

Properties props = System.getProperties();
Enumeration e = props.propertyNames();

	while(e.hasMoreElements()) {
		String key = (String) e.nextElement();
		try {
			out.println("<p>");
			out.println(key + "\t=\t" + props.getProperty(key));			
		} catch(Exception ie) {
		}

	}

	
%>