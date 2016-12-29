<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Architect</title>
</head>
<body>
	<h1>Architect</h1>
	<form method="post" action="/upload.controller">
		<table>
			<tr>
				<td>Upload</td>
				<td><input name="localBasePath" type="text" /></td>
				<td><input name="remoteBasePath" type="text" /></td>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
	<form method="post" action="/run.controller">
		<table>
			<tr>
				<td>Command</td>
				<td><input name="command" type="text" /></td>
				<td><input name="minWaitingTime" type="text" value="5"/></td>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
	<form method="post" action="/listFiles.controller">
		<table>
			<tr>
				<td>List Files</td>
				<td><input name="remoteFolder" type="text" /></td>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
	<form method="post" action="/printLog.controller">
		<table>
			<tr>
				<td>Print Log</td>
				<td><input name="remoteFile" type="text" /></td>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
	<form method="post" action="/logout.controller">
		<table>
			<tr>
				<td>Logout</td>
				<td><input type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>