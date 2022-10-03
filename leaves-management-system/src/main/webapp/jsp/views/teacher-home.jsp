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
		<h3 class="my-4">Teacher Home</h3>

		<div class="row">
			<div class="col-auto">
				<input type="date" name="targetDate" value="${ targetDate }" class="form-control" />
			</div>
			<div class="col-auto">
				<button class="btn btn-outline-success" role="submit"><i class="bi bi-search"></i> Search</button>
			</div>
		</div>
		
		<div class="row g-3 mt-4">
			<c:forEach items="${ list }" var="item">
				<div class="col-4">
					<div class="card">
						<div class="card-body">
							<h4>${ item.teacher }</h4>
							<div class="text-secondary">${ item.details }</div>
							<span>${ item.startDate }</span>
							
							<div class="row mt-4">
								<div class="col">
									<h5 class=""><i class="bi bi-people-fill text-success"></i> ${ item.students }</h5>
									<span class="text-secondary">Students</span>
								</div>
								
								<div class="col">
									<h5 class=""><i class="bi bi-person-dash-fill text-danger"></i> ${ item.leaves }</h5>
									<span class="text-secondary">Leaves</span>
								</div>
								
								<div class="row mt-4">
									<div class="col">
										<c:url value="/classes/${ item.classId }" var="details"></c:url>
										<a href="${ details }" class="btn btn-outline-success">
											<i class="bi bi-send"></i> Show Details
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
	</div>

</body>
</html>