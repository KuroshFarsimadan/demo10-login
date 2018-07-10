<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java EE - Demo 10</title>
</head>
<body>
	<h1>Java EE</h1>
	<h2>DEMO 10</h2>

	<h3>Content</h3>
	<ul>
		<li>Signin</li>
		<li>Logout</li>
		<li>WebUser information in session</li>
		<li>Encrypted password comparison</li>
	</ul>
	<h3>Functionality</h3>
	<p>
	SiteServlet will handle the redirection to dashboard or inside the
	system. Signin, logout, and register functionalities have their 
	own servlets. User information will be saved in signin to a session
	attribute such as "user".		
		</p>
	<h3>Link</h3>
	<p>
		<a href="site">site</a>
	</p>
</body>
</html>