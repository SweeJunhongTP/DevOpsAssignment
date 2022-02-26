
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blank page 2</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
		<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Food Blog Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Home.jsp"
				class="nav-link">home</a></li>
		</ul>

		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Blankpage1.jsp"
				class="nav-link">blank1</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/Blankpage2.jsp"
				class="nav-link">blank2</a></li>
		</ul>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/RecipeManagement/recipe"
				class="nav-link">recipe</a></li>
		</ul>
	</nav>

<h2> page 2</h2>
</body>
</html>