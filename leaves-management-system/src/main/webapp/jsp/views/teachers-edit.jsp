<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Teachers</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="teachers"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4"> ${ param.id > 0 ? 'Edit' : 'Add New'  } Teacher</h3>
		
		<c:url value="/teachers" var="save"></c:url>
		<sf:form action="${save}" modelAttribute="form" method="post" cssClass="col-lg-6 col-md-9 col-sm-12">
			<sf:hidden path="id"/>
			
			<div class="mb-3">
				<label for="" class="form-label">Name</label>
				<sf:input path="name" cssClass="form-control" placeholder="Enter Teacher Name"/>
				<sf:errors path="name" cssClass="text-warning"></sf:errors>
			</div>
			
			<div class="mb-3">
				<label for="" class="form-label">Phone</label>
				<sf:input path="phone" type="tel" cssClass="form-control" placeholder="Enter Teacher Phone"/>
				<sf:errors path="phone" cssClass="text-warning" ></sf:errors>
			</div>
			
			<div class="mb-3 ${ empty param.id ? '' : 'd-none' }">
				<label for="" class="form-label">Email</label>
				<sf:input path="email" type="email" cssClass="form-control" placeholder="Enter Teacher Email"/>
				<sf:errors path="email" cssClass="text-warning"></sf:errors>
			</div>
			
			<div class="mb-3">
				<label for="" class="form-label">Assign Date</label>
				<sf:input path="assignDate" type="date" cssClass="form-control" />
				<sf:errors path="assignDate" cssClass="text-warning"></sf:errors>
			</div>
			
			<div class="">
				<button class="btn btn-outline-danger" type="submit"> <i class="bi bi-save"></i> Save</button>
			</div>
			
		</sf:form>
		
	</div>
	
</body>
</html>