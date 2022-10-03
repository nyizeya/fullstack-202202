<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Classes</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>

	<div class="container">
		<h3 class="my-4">Class Details</h3>

		<div class="card mb-4">
			<div class="card-header">Class Information</div>
			<div class="card-body row">
				<div class="col">
					<label for="" class="form-label">Teacher</label>
					<span class="form-control">${ dto.classInfo.teacherName }</span>
				</div>
				<div class="col">
					<label for="" class="form-label">Start Date</label>
					<span class="form-control">${ dto.classInfo.startDate }</span>
				</div>
				<div class="col">
					<label for="" class="form-label">Duration</label>
					<span class="form-control">${ dto.classInfo.months } Months</span>
				</div>
				<div class="col">
					<label for="" class="form-label">Description</label>
					<span class="form-control">${ dto.classInfo.description }</span>
				</div>
			</div>
		</div>

		<div class="d-flex justify-content-between mb-4">
			<ul class="nav nav-pills">
				<li class="nav-item">
					<button class="nav-link active" data-bs-toggle="pill" data-bs-target="#registrations">
						<i class="bi bi-people-fill"></i> Registration</button>
				</li>
				<li class="nav-item">
					<button class="nav-link" data-bs-toggle="pill" data-bs-target="#leaves">
						<i class="bi bi-person-x-fill"></i> Leaves Application</button>
				</li>
			</ul>
			
			<div>
				<c:url value="/classes/edit" var="editClass">
					<c:param name="id" value="${ dto.classInfo.id }"></c:param>
				</c:url>
				<a href="${editClass}" class="btn btn-outline-primary me-2">
					<i class="bi bi-pencil"></i> Edit Class</a>
				
				<c:url value="/classes/registration" var="addNew">
					<c:param name="classId" value="${ dto.classInfo.id }"></c:param>
					<c:param name="teacherName" value="${ dto.classInfo.teacherName }"></c:param>
					<c:param name="startDate" value="${ dto.classInfo.startDate }"></c:param>
				</c:url>
				<a href="${addNew}" class="btn btn-outline-danger">
					<i class="bi bi-plus-lg"></i> Add New Registration</a>
			</div>
		</div>
		
		<div class="tab-content" id="contents">
			<div class="tab-pane fade show active" id="registrations">
				<!-- Class Registration -->
				<c:choose>
					<c:when test="${ empty dto.registrations }">
						<div class="alert alert-info">There Is No Registration Data</div>
					</c:when>
					<c:otherwise>
						<c:import url="/jsp/include/class-registration.jsp"></c:import>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="tab-pane fade" id="leaves">
				<!-- Class Leaves -->
				<c:choose>
					<c:when test="${ empty dto.leaves }">
						<div class="alert alert-info">There Is No Leaves Data</div>
					</c:when>
					<c:otherwise>
						<c:import url="/jsp/include/class-leaves.jsp"></c:import>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

	</div>

</body>
</html>