<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> foodd blog </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/RecipeManagement/recipe"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${recipe != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${recipe == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${recipe != null}">
					Edit Recipe
					</c:if>
						<c:if test="${recipe == null}">
					Add New Recipe
					</c:if>
					</h2>
				</caption>
				<c:if test="${recipe != null}">
					<input type="hidden" name="oriName"
						value="<c:out value='${recipe.recipeName}'/>" />
				</c:if>
				<fieldset class="form-group">
					<label>Recipe Title</label> <input type="text"
						value="<c:out value='${recipe.recipeName}'/>" class="form-control"
						name=recipeName required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>description</label> <input type="text"
						value="<c:out value='${recipe.description}'/>"
						class="form-control" name="description">
				</fieldset>
				<fieldset class="form-group">
					<label>Posted On</label> <input type="text"
						value="<c:out value='${recipe.createdOn}'/>" class="form-control"
						name="createdOn">
				</fieldset>
				<fieldset class="form-group">
					<label> Author </label> <input type="text"
						value="<c:out value='${recipe.author}'/>" class="form-control"
						name="author">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>