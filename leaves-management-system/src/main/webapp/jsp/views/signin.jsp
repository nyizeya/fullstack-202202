<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | SignIn</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<c:url var="commonCSS" value="/resources/application.css"></c:url>
<link rel="stylesheet" href="${ commonCSS }" />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="vh-100">
	
	<div class="d-flex vh-100 justify-content-center align-items-center">
		<div class="card login-form">
			<div class="card-header">
				<i class="bi bi-door-open"></i> Sign In
			</div>
			<div class="card-body">
				<c:url value="/signin" var="signIn"></c:url>
				<sf:form action="${signIn}" method="post">
					<c:if test="${ not empty param.error }">
						<div class="alert alert-warning">Login Error</div>
					</c:if>
					<div class="mb-3">
						<label for="" class="form-label">Email</label>
						<input type="email" name="username" placeholder="Enter Your Email Address" class="form-control" />
					</div>
					
					<div class="mb-3">
						<label for="" class="form-label">Password</label>
						<input type="password" name="password" 
							autocapitalize="off"
							placeholder="Enter Your Password" class="form-control" />
					</div>
					
					<div class="mb-3">
						<button class="btn btn-outline-success" type="submit"> 
							<i class="bi bi-box-arrow-right"></i> Sign In
						</button>
					</div>
				</sf:form>
			</div>
		</div>
	</div>
	
</body>
</html>