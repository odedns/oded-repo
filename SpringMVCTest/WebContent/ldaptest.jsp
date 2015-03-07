<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="javax.naming.*,java.util.*,javax.naming.directory.*" %>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%


    Hashtable<String,String> env = new Hashtable<String,String>();

    String sp = "com.sun.jndi.ldap.LdapCtxFactory";
    env.put(Context.INITIAL_CONTEXT_FACTORY, sp);

    //String ldapUrl = "ldap://dewdfils.global.corp.sap:389";
    String ldapUrl = "ldaps://dewdfils.global.corp.sap:636";
    env.put(Context.PROVIDER_URL, ldapUrl);
 // Authenticate as S. User and password "mysecret"
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.SECURITY_PRINCIPAL, "CN=C5182577,OU=C,OU=Identities,DC=global,DC=corp,DC=sap");
    env.put(Context.SECURITY_CREDENTIALS, "ADmigr8tion");
    env.put(Context.REFERRAL, "follow");
    env.put(Context.SECURITY_PROTOCOL, "ssl");

    DirContext dctx = new InitialDirContext(env);

    String base = "DC=global,DC=corp,DC=sap";

    SearchControls sc = new SearchControls();
    String[] attributeFilter = { "cn", "mail" };
    sc.setReturningAttributes(attributeFilter);
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

    String filter = "(&(objectclass=user)(samAccountName=i070659))";

    NamingEnumeration results = dctx.search(base, filter, sc);
    while (results.hasMore()) {
      SearchResult sr = (SearchResult) results.next();
      Attributes attrs = sr.getAttributes();

      Attribute attr = attrs.get("cn");
      out.print(attr.get() + ": ");
      attr = attrs.get("mail");
      out.println(attr.get());
    }
    dctx.close();
  


%>



</body>
</html>