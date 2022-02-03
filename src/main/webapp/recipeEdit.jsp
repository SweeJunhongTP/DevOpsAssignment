<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${user != null}">
Edit User
</c:if>
						<c:if test="${user == null}">
Add New User
</c:if>
					</h2>
				</caption>
				<c:if test="${user != null}">
					<input type="hidden" name="oriName"
						value="<c:out 
value='${user.name}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>User Name</label> <input type="text"
						value="<c:out 
value='${user.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Password</label> <input type="text"
						value="<c:out 
value='${user.password}' />" class="form-control"
						name="password">
				</fieldset>
				<fieldset class="form-group">
					<label>User Email</label> <input type="text"
						value="<c:out 
value='${user.email}' />" class="form-control"
						name="email">
				</fieldset>
				<fieldset class="form-group">
					<label> Language</label> <input type="text"
						value="<c:out 
value='${user.language}' />" class="form-control"
						name="language">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>