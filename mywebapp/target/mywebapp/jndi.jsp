<%@page import="javax.naming.*,java.util.*" %>

<html>
<body>
<h2> JDNI Test Page </h2>

<%

try {
	String url       = "iiop://hsn-xsm-01-190r:2813";

	Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY,
			"com.ibm.websphere.naming.WsnInitialContextFactory");
		prop.put(Context.PROVIDER_URL, url);
		//prop.put("java.naming.factory.url.pkgs","com.ibm.websphere.naming");
 		Context ctx          = new InitialContext(prop);
 		
 		ctx.bind("test", new String("testData"));
 		String s = (String) ctx.lookup("test");
 		out.println("<p>got from jndi : "+ s); 
 		
		out.println("<p>End Mytest session.Client...\n");
 
} catch(Exception e) {
	e.printStackTrace();
} 


%>

</body>
</html>