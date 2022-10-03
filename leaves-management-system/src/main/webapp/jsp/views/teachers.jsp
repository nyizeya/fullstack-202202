<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Teachers</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

<c:url value="/resources/application.css" var="commonCss"></c:url>
<link rel="stylesheet" href="${commonCss}" />
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="teachers"></c:param>
	</c:import>

	<div class="container">
		<h3 class="my-4">Teacher Management</h3>

		<!-- Search Bar -->
		<form class="row mb-4">
			<div class="col-auto">
				<label for="" class="form-label">Name</label> 
				<input type="text" value="${ param.name }"
					name="name" placeholder="Search Name" class="form-control" />
			</div>

			<div class="col-auto">
				<label for="" class="form-label">Phone</label> 
				<input type="tel" value="${ param.phone }"
					name="phone" placeholder="Search Phone" class="form-control" />
			</div>

			<div class="col-auto">
				<label for="" class="form-label">Email</label> 
				<input type="email" value="${ param.email }"
					name="email" placeholder="Search Name" class="form-control" />
			</div>

			<div class="col-auto btn-wrapper">
				<button class="btn btn-outline-success me-2" type="submit">
					<i class="bi bi-search"></i> Search
				</button>
				<c:url value="teachers/edit" var="addNew"></c:url>
				<a href="${addNew}" class="btn btn-outline-danger"> <i
					class="bi bi-plus-lg"></i> Add New Teacher
				</a>
			</div>
		</form>

		<!-- Table View -->
		<c:choose>
			<c:when test="${ empty list }">
				<div class="alert alert-info">There Is No Data</div>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Assign Date</th>
							<th>Classes</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ list }" var="t">
							<tr>
								<td>${ t.id }</td>
								<td>${ t.name }</td>
								<td>${ t.phone }</td>
								<td>${ t.email }</td>
								<td>${ t.assignDate }</td>
								<td>${ t.classCount }</td>
								<td>
									<c:url value="/teachers/edit" var="edit">
										<c:param name="id" value="${ t.id }"></c:param>
									</c:url> 
									<a href="${edit}"><i class="bi bi-pencil-square"></i></a>
								</td>
							</tr>						
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>

	</div>

</body>
</html>