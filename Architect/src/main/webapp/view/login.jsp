<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Architect</title>
</head>
<body>
	<h1>Architect</h1>
	<form method="post" action="/login.controller">
		<table>
			<tr>
				<td>User Id</td>
				<td><input name="userId" type="text" /></td>
			</tr>
			<tr>
				<td>Host</td>
				<td><input name="host" type="text" /></td>
			</tr>
			<tr>
				<td>Port</td>
				<td><input name="port" type="text" /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input name="username" type="text" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="text" /></td>
			</tr>
			<tr>
				<td><input name="Submit" type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>