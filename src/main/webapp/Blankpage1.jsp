<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>1</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Food Blog Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a 
			href="<%=request.getContextPath()%>/home.jsp"
				class="nav-link">home</a></li>
		</ul>
	
		<ul class="navbar-nav">
			<li><a 
			href="<%=request.getContextPath()%>/Blankpage1.jsp"
				class="nav-link">1</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a 
			href="<%=request.getContextPath()%>/Blankpage1.jsp"
				class="nav-link">2</a></li>
		</ul>
	</nav>

<h2> page 1</h2>

<form action ="RegisterServlet" method="post">
Name: <input type= "text" name="userName">
Password: <input type = "password" name="password">
Email: <input type="text" name= "email">
Gender: <select name="gender">
<option>Male</option>
<option>Female</option>
<option>Others</option>
</select>
<input type = "submit" value="Submit"/>
</form>

</body>
</html>