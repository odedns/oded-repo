<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<%@page import="javax.naming.*,javax.jms.*" %>


<META http-equiv="Content-Type"
	content="text/html; charset=windows-1255">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>jms.jsp</TITLE>
</HEAD>
<BODY>
<P>Jms Test.</P>

<%
	try {
		InitialContext ctx = new InitialContext();
		QueueConnectionFactory qcf = (QueueConnectionFactory) ctx.lookup("jms/QCF");
	//out.println("<p> got QueueConnectionFactory");
		System.out.println("got connection Factory");
		
		QueueConnection qcon = qcf.createQueueConnection();
	    QueueSession qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    	Queue queue = (Queue) ctx.lookup("jms/queue1");
	    QueueSender qsender = qsession.createSender(queue);
    	TextMessage msg = qsession.createTextMessage();
	    qcon.start();
        msg.setText("foo message");
	    qsender.send(msg);
	    
	    QueueReceiver qr = qsession.createReceiver(queue);
	    msg = (TextMessage) qr.receive();
	    out.println("<p> got message: " + msg.getText());
	    
	} catch(Exception e) {
		e.printStackTrace();
		// out.println("error : " + e);
	}
	

 %>

</BODY>
</HTML>
