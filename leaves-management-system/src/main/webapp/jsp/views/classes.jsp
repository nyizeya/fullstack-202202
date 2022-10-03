<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Classes</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

<c:url value="/resources/application.css" var="commonCss"></c:url>
<link rel="stylesheet" href="${commonCss}" />
</head>
<body>
	
	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4">Classes Management</h3>
		
		<form class="row mb-4">
			<div class="col-auto">
				<label for="" class="form-label">Teacher</label>
				<input type="text" name="teacher" class="form-control"
				 value="${ param.teacher }" placeholder="Search Teacher Name" />
			</div>
			
			<div class="col-auto">
				<label for="" class="form-label">Date From</label>
				<input type="date" name="from" class="form-control" value="${ param.from }" />
			</div>
			
			<div class="col-auto">
				<label for="" class="form-label">Date To</label>
				<input type="date" name="to" class="form-control" value="${ param.to }" />
			</div>
			
			<div class="col-auto btn-wrapper">
				<button class="btn btn-outline-success me-2" type="submit">
					<i class="bi bi-search"></i> Search
				</button>
				<c:url value="classes/edit" var="addNew"></c:url>
				<a href="${addNew}" class="btn btn-outline-danger">
					<i class="bi bi-plus-lg"></i> Add New Class
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
							<th>Teacher</th>
							<th>Teacher Phone</th>
							<th>Start Date</th>
							<th>Months</th>
							<th>Students</th>
							<th>Description</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ list }" var="c">
							<tr>
								<td>${ c.id }</td>
								<td>${ c.teacherName }</td>
								<td>${ c.teacherPhone }</td>
								<td>${ c.startDate }</td>
								<td>${ c.months } Months</td>
								<td>${ c.studentCount }</td>
								<td>${ c.description }</td>
								<td>
									<c:url value="/classes/edit" var="edit">
										<c:param name="id" value="${ c.id }"></c:param>
									</c:url>
									<a href="${edit}"><i class="bi bi-pencil-square me-3"></i></a>
									
									<c:url value="/classes/${ c.id }" var="details"></c:url>
									<a href="${details}"><i class="bi bi-cursor"></i></a>
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