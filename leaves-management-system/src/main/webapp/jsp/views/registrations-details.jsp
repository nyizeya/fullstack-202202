<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h3 class="my-4">Registration Details</h3>
		
		<div class="row">
		
			<div class="col">
				<div class="card">
					<div class="card-header">
						Student Information
					</div>
					<div class="card-body">
						<div class="mb-3">
							<label class="form-label">Student Name</label>
							<span class="form-control">${ dto.student.name }</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Student Phone</label>
							<span class="form-control">${ dto.student.phone }</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Student Email</label>
							<span class="form-control">${ dto.student.email }</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Registration Date</label>
							<span class="form-control">${ dto.registDate }</span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col">
				<div class="card">
					<div class="card-header">
						Class Information
					</div>
					<div class="card-body">
						<div class="mb-3">
							<label class="form-label">Teacher Name</label>
							<span class="form-control">${ dto.classInfo.teacherName }</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Start Date</label>
							<span class="form-control">${ dto.classInfo.startDate }</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Duration</label>
							<span class="form-control">${ dto.classInfo.months } Months</span>
						</div>
						<div class="mb-3">
							<label class="form-label">Description</label>
							<span class="form-control">${ dto.classInfo.description }</span>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="mt-4">
			
			<c:url var="classDetails" value="/classes/${ dto.classInfo.id }"></c:url>
			<a href="${classDetails}" class="btn btn-outline-primary">
				<i class="bi bi-mortarboard"> Class Details</i>
			</a>
			
			<c:url value="/classes/registration" var="edit">
				<c:param name="classId" value="${ dto.classInfo.id }"></c:param>
				<c:param name="studentId" value="${ dto.student.id }"></c:param>
				<c:param name="teacherName" value="${ dto.classInfo.teacherName }"></c:param>
				<c:param name="startDate" value="${ dto.classInfo.startDate }"></c:param>
			</c:url>
			<a href="${edit}" class="btn btn-outline-danger ms-2"> <i class="bi bi-pencil-square"></i> Edit Registration</a>
			
		</div>
		
	</div>
	
</body>
</html>