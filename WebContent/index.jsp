<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="de.hsw.jee.guestbook.service.GuestbookServiceImpl"%>
<%@ page import="de.hsw.jee.guestbook.service.GuestbookService" %>
<%@ page import="de.hsw.jee.guestbook.service.GuestbookServiceImpl" %>
<%@ page import="de.hsw.jee.guestbook.model.Eintrag" %>

<%!
	GuestbookService guestbook = de.hsw.jee.guestbook.utils.BeanUtil
		.lookupLocal(GuestbookServiceImpl.class, GuestbookService.class);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Guestbook</title>
</head>
<body>

	<h1>Guestbook</h1>

	<% for(Eintrag eintrag : guestbook.alleEintraege()) {%>
		
		<h2>Eintrag von <%= eintrag.getBenutzer().getName() %></h2>

		<p><%= eintrag.getNachricht() %></p>
	
	<% }%>
	
	<form action="/guestbook/guestbook" method="POST">
		<table>
			<tr>
				<td><label>Ihre Nachricht</label></td>
				<td><textarea name="nachricht" ></textarea></td>
				</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Anmelden"></td>
			</tr>	
		</table>
	</form>
	
</body>
</html>