<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Leaves | Leaves</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	
	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="leaves"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4">Leaves List</h3>
		
		<!-- Search Bar -->
		<form class="row mb-4">

			<div class="col-auto">
				<label for="" class="form-label">Date From</label> 
				<input type="date" name="from" value="${ param.from }" 
					class="form-control" />
			</div>

			<div class="col-auto">
				<label for="" class="form-label">Date To</label> 
				<input type="date" name="to" value="${ param.to }" 
					class="form-control" />
			</div>

			<div class="col-auto btn-wrapper">
				<button class="btn btn-outline-success me-2" type="submit">
					<i class="bi bi-search"></i> Search
				</button>
				
			</div>
		</form>
		
		<c:choose>
			<c:when test="${ empty dto }">
				<div class="alert alert-info">There Is No Data</div>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Class Info</th>
							<th>Teacher</th>
							<th>Apply On</th>
							<th>Leave Start</th>
							<th>Leave Days</th>
							<th>Reason</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ dto }" var="t">
							<tr>
								<td>${ t.classInfo }</td>
								<td>${ t.teacher }</td>
								<td>${ t.applyDate }</td>
								<td>${ t.startDate }</td>
								<td>${ t.days }</td>
								<td>${ t.reason }</td>
							</tr>						
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
	</div>
	
</body>
</html>