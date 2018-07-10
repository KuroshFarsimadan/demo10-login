<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>

	<c:if test="${not empty param.success }">
		<p>Registering was successful. Signin In!</p>
	</c:if>

	<c:if test="${not empty error }">
		<p>
			<c:out value="${error}" />
		</p>
	</c:if>

	<div id="login">
		<form action="login" method="post">
			<h1>Signin</h1>
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="username"
						value="<c:out value="${prev_login_username}"/>" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><button type="submit">Sign In</button></td>
				</tr>
			</table>
		</form>
	</div>

	<form action="register" method="post">
		<h1>Register</h1>
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"
					value="<c:out value="${prev_reg_username}"/>" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Password again</td>
				<td><input type="password" name="password2" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><button type="submit">Register</button></td>
			</tr>
		</table>
	</form>

</body>
</html>

