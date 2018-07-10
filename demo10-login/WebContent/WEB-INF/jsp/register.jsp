<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
	<c:if test="${not empty param.success }">
		<p>Registering was successful!</p>
	</c:if>
	<c:if test="${not empty error }">
		<p style="color:red">
			<c:out value="${error}" />
		</p>
	</c:if>
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
				<td>Repeat the password</td>
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