<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Guestbook</title>
</head>
<body>

	<h1>Guestbook - Registrierung</h1>
	
	<form action="/guestbook/auth/register" method="POST">
		<table>
			<tr>
				<td><label>Name</label></td>
				<td><input name="benutzerName" ></input></td>
			</tr>
			<tr>
				<td><label>Passwort</label></td>
				<td><input type="password" name="password" ></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="New Account"></td>
			</tr>	
		</table>
	</form>
	
</body>
</html>