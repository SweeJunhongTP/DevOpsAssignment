<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Recipe</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h2 class="text-center my-5">Create New Recipe</h2>
		<div class="col-md-6 offset-md-3">
			<form action="RecipeServlet" method="post">
				<div class="form-group">
					<label for="recipeName"> Recipe Title</label> 
					<input class="form-control" type="text"
						name="recipeName" placeholder="Give it a attractive title!">
				</div>
				<div class="form-group">
					<label for="description"> Description</label> 
					<input type="text" class="form-control" 
						name="description" placeholder="description">
				</div>
				<div class="form-group">
					<label for="createdOn"> Create on:</label>
					 <input type="date" class="form-control" 
						name="createdOn">
				</div>
				<div class="form-group">
					<label for="author"> Author:</label> 
					<input type="text" class="form-control" 
						name="author" placeholder="username">
				</div>

				<input type="submit" value="call Servlet" />
			</form>
		</div>
	</div>
</body>
</html>