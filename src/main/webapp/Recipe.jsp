<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">	
<title>Recipe</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
			<li><a href="<%=request.getContextPath()%>/Recipe.jsp"
				class="nav-link">recipe</a></li>
		</ul>
	</nav>


	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Recipes</h3>
			<hr>
			<div class="container text-left">
				
				<a href="<%=request.getContextPath()%>/CreateRecipe.jsp"
					class="btn btn-success">Add New Recipe</a>
			</div>
			<br>
			
			<table class="table">
				<thead>
					<tr>
						<th>Recipe title</th>
						<th>Description</th>
						<th>Posted on</th>
						<th>Author</th>
						<th>Actions</th>
					</tr>
				</thead>
			
				<tbody>
					<c:forEach var="recipe" items="${listRecipe}">
						
						<tr>
							<td><c:out value="${recipe.recipeName}" /></td>
							<td><c:out value="${recipe.description}" /></td>
							<td><c:out value="${recipe.createdOn}" /></td>
							<td><c:out value="${recipe.author}" /></td>

							<td>
							<a href="edit?recipeName=<c:out value='${recipe.recipeName}'/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
							<a href="delete?recipeName=<c:out value='${recipe.recipeName}'/>">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>