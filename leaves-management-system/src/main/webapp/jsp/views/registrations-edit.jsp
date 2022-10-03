<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Registrations</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4">${ param.studentId > 0 ? 'Edit' : 'Add New' } Registration</h3>
		
		<div class="row">
			<!-- <c:url value="/classes/registration" var="save"></c:url> -->
			<sf:form method="post" modelAttribute="registForm" cssClass="col-6" >
				<sf:hidden path="classId"/>
				<sf:hidden path="studentId"/>
				<sf:hidden path="registDate"/>
				
				<div class="mb-3">
					<label for="" class="form-label">Start Date</label>
					<span class="form-control">${ param.startDate }</span>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Teacher</label>
					<span class="form-control">${ param.teacherName }</span>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Student Name</label>
					<sf:input path="studentName" placeholder="Enter Student Name" cssClass="form-control"/>
					<sf:errors path="studentName" cssClass="text-warning"></sf:errors>
				</div>
				
				<div class="mb-3 ${ empty param.studentId ? '' : 'd-none' }">
					<label for="" class="form-label">Email</label>
					<sf:input path="email" type="email" placeholder="Enter Student Email" cssClass="form-control"/>
					<sf:errors path="email" cssClass="text-warning"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Phone</label>
					<sf:input path="phone" type="tel" placeholder="Enter Student phone" cssClass="form-control"/>
					<sf:errors path="phone" cssClass="text-warning"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label for="" class="form-label">Last Education</label>
					<sf:input path="education" placeholder="Enter Last Education" cssClass="form-control"/>
				</div>
				
				<div>
					<button class="btn btn-outline-success"><i class="bi bi-save"></i> Save</button>
				</div>
				
			</sf:form>
			
		</div>
	</div>
	
</body>
</html>