<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>create recipe</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
	<form action="RecipeServlet" method="post">
		Recipe title: <input type="text" name="recipeName"> 
		
		Description: <input type="text" name="description"> 
		
		Create on: <input type="date" name="createdOn">
		
		Author: <input type="text" name="author"> 
		
		<input type="submit" value="call Servlet" />
	</form>
	
</body>
</html>