<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp"></c:import>

	<div class="container">

		<h3 class="my-4">Student Home</h3>

		<div class="row">

			<div class="col-3">
				<div class="card">
					<div class="card-header">Personal Information</div>
					<div class="card-body">
						<div class="">
							<span class="text-secondary">Name</span>
							<h5>${ dto.student.name }</h5>
						</div>
						<div class="mt-3">
							<span class="text-secondary">Phone</span>
							<h5>${ dto.student.phone }</h5>
						</div>
						<div class="mt-3">
							<span class="text-secondary">Email</span>
							<h5>${ dto.student.email }</h5>
						</div>
						<div class="mt-3">
							<span class="text-secondary">Education</span>
							<h5>${ dto.student.education }</h5>
						</div>
					</div>
				</div>
			</div>

			<div class="col">
				<div class="row g-3">
					<c:forEach items="${ dto.registrations }" var="item">
						<div class="col-4">
							<div class="card card-body">
								<h5>${ item.classInfo }</h5>

								<div class="d-flex justify-content-between text-secondary mb-4">
									<span>${ item.startDate }</span> 
									<span>${ item.teacher }</span>
								</div>
								
								<div>
									<c:url value="/leaves/edit" var="edit">
										<c:param name="classId" value="${ item.classId }"></c:param>
										<c:param name="studentId" value="${ dto.student.id }"></c:param>
									</c:url>
									<a href="${ edit }" class="btn btn-outline-success">
										<i class="bi bi-send"></i> Apply Leave
									</a>
								</div>
								
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>

</body>
</html>